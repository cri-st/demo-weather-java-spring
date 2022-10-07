package st.cri.demoweather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {
    private String name;
    private String province;
    private WeatherDetailsDto weather;
}
