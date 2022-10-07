package st.cri.demoweather.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WeatherListDto {
    private List<WeatherDto> weatherList;

    public WeatherListDto() {
        weatherList = new ArrayList<>();
    }
}
