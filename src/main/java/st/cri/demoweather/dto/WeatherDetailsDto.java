package st.cri.demoweather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * The Weather details dto.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDetailsDto {

  private Integer temp;
  private String description;
}
