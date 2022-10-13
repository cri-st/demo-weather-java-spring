package st.cri.demoweather.service;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.cri.demoweather.dto.WeatherDto;
import st.cri.demoweather.dto.mapper.CityWeatherMapper;
import st.cri.demoweather.model.CityWeather;
import st.cri.demoweather.repository.CityWeatherRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The Weather service.
 */
@Service
public class WeatherService {

  private final CityWeatherMapper cityWeatherMapper;
  private final CityWeatherRepository cityWeatherRepository;

  /**
   * Instantiates a new Weather service.
   *
   * @param cityWeatherMapper     the city weather mapper
   * @param cityWeatherRepository the city weather repository
   */
  public WeatherService(CityWeatherMapper cityWeatherMapper,
      CityWeatherRepository cityWeatherRepository) {
    this.cityWeatherMapper = cityWeatherMapper;
    this.cityWeatherRepository = cityWeatherRepository;
  }

  /**
   * Update weather every 5 minutes.
   */
  @Scheduled(cron = "0 */5 * * * *")
  public void updateWeather() {
    final RestTemplate restTemplate = new RestTemplate();
    final ResponseEntity<WeatherDto[]> response = restTemplate.getForEntity(
        "https://ws.smn.gob.ar/map_items/weather", WeatherDto[].class);
    for (final WeatherDto dto : Objects.requireNonNull(response.getBody())) {
      saveOrUpdate(cityWeatherMapper.toCityWeather(dto));
    }
  }

  private void saveOrUpdate(CityWeather cityWeather) {
    final Optional<CityWeather> result = cityWeatherRepository.findByNameAndProvince(
        cityWeather.getName(), cityWeather.getProvince());
    result.ifPresent(actualCityWeather -> {
      actualCityWeather.setTemperature(cityWeather.getTemperature());
      actualCityWeather.setWeatherDescription(cityWeather.getWeatherDescription());
      cityWeatherRepository.save(actualCityWeather);
    });
    if (result.isEmpty()) {
      cityWeatherRepository.save(cityWeather);
    }
  }

  /**
   * Gets all.
   *
   * @return the all
   */
  public List<CityWeather> getAll() {
    return cityWeatherRepository.findAll();
  }
}
