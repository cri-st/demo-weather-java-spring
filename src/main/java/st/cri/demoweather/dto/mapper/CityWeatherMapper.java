package st.cri.demoweather.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.cri.demoweather.dto.CityWeatherDto;
import st.cri.demoweather.dto.WeatherDto;
import st.cri.demoweather.model.CityWeather;

/**
 * The City weather mapper.
 */
@Mapper(componentModel = "spring")
public interface CityWeatherMapper {

  /**
   * City weather DTO to city weather.
   *
   * @param weatherDto the weather dto
   * @return the city weather
   */
  @Mapping(target = "name", source = "name")
  @Mapping(target = "province", source = "province")
  @Mapping(target = "temperature", source = "weather.temp")
  @Mapping(target = "weatherDescription", source = "weather.description")
  @Mapping(target = "id", ignore = true)
  CityWeather toCityWeather(WeatherDto weatherDto);

  /**
   *  City weather to city weather dto.
   *
   * @param cityWeather the city weather
   * @return the city weather dto
   */
  @Mapping(target = "cityName", source = "name")
  @Mapping(target = "province", source = "province")
  @Mapping(target = "temperature", source = "temperature")
  @Mapping(target = "description", source = "weatherDescription")
  CityWeatherDto toCityWeatherDto(CityWeather cityWeather);
}
