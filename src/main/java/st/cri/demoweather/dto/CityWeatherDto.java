package st.cri.demoweather.dto;

import lombok.Data;

/**
 * The City weather dto.
 */
@Data
public class CityWeatherDto {

  private String cityName;
  private String province;
  private Integer temperature;
  private String description;
}
