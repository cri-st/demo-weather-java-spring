package st.cri.demoweather.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.cri.demoweather.dto.CityWeatherDto;
import st.cri.demoweather.dto.mapper.CityWeatherMapper;
import st.cri.demoweather.service.WeatherService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/weather")
public class CityWeatherController {
    private final WeatherService weatherService;
    private final CityWeatherMapper cityWeatherMapper;

    public CityWeatherController(WeatherService weatherService, CityWeatherMapper cityWeatherMapper) {
        this.weatherService = weatherService;
        this.cityWeatherMapper = cityWeatherMapper;
    }

    @GetMapping("/all")
    public List<CityWeatherDto> getAll() {
        return weatherService.getAll().stream().map(cityWeatherMapper::toCityWeatherDto).collect(Collectors.toList());
    }
}
