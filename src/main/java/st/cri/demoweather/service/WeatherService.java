package st.cri.demoweather.service;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.cri.demoweather.dto.WeatherDto;
import st.cri.demoweather.dto.mapper.CityWeatherMapper;
import st.cri.demoweather.dto.mapper.UserMapper;
import st.cri.demoweather.model.CityWeather;
import st.cri.demoweather.repository.CityWeatherRepository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WeatherService {

    private final CityWeatherMapper cityWeatherMapper;
    private final CityWeatherRepository cityWeatherRepository;

    public WeatherService(CityWeatherMapper cityWeatherMapper, CityWeatherRepository cityWeatherRepository) {
        this.cityWeatherMapper = cityWeatherMapper;
        this.cityWeatherRepository = cityWeatherRepository;
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void updateWeather() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WeatherDto[]> response = restTemplate.getForEntity("https://ws.smn.gob.ar/map_items/weather", WeatherDto[].class);
        for (WeatherDto dto: Objects.requireNonNull(response.getBody())) {
            saveOrUpdate(cityWeatherMapper.toCityWeather(dto));
        }
    }

    private void saveOrUpdate(CityWeather cityWeather) {
        Optional<CityWeather> result = cityWeatherRepository.findByNameAndProvince(cityWeather.getName(), cityWeather.getProvince());
        result.ifPresent(actualCityWeather -> {
            actualCityWeather.setTemperature(cityWeather.getTemperature());
            actualCityWeather.setWeatherDescription(cityWeather.getWeatherDescription());
            cityWeatherRepository.save(actualCityWeather);
        });
        if (result.isEmpty()) {
            cityWeatherRepository.save(cityWeather);
        }
    }

    public List<CityWeather> getAll() {
        return cityWeatherRepository.findAll();
    }
}
