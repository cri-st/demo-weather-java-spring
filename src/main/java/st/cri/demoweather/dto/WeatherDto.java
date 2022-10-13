package st.cri.demoweather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * The Weather dto.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {

  private String name;
  private String province;
  private WeatherDetailsDto weather;
}
