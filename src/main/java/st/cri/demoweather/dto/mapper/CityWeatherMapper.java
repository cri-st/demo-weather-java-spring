package st.cri.demoweather.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import st.cri.demoweather.dto.CityWeatherDto;
import st.cri.demoweather.dto.WeatherDto;
import st.cri.demoweather.model.CityWeather;

@Mapper(componentModel = "spring")
public interface CityWeatherMapper {
    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "province", source = "province"),
            @Mapping(target = "temperature", source = "weather.temp"),
            @Mapping(target = "weatherDescription", source = "weather.description"),
            @Mapping(target = "id", ignore = true)
    })
    CityWeather toCityWeather(WeatherDto weatherDto);

    @Mappings({
            @Mapping(target = "city_name", source = "name"),
            @Mapping(target = "province", source = "province"),
            @Mapping(target = "temperature", source = "temperature"),
            @Mapping(target = "weatherDescription", source = "weatherDescription")
    })
    CityWeatherDto toCityWeatherDto(CityWeather cityWeather);
}
