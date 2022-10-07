package st.cri.demoweather.dto;

import lombok.Data;

@Data
public class CityWeatherDto {
    private String city_name;
    private String province;
    private Integer temperature;
    private String weatherDescription;
}
