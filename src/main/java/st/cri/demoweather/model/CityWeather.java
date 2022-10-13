package st.cri.demoweather.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * The City weather.
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "city_weather")
public class CityWeather {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

  @NotBlank
  @Column(name = "name", nullable = false)
  private String name;

  @NotBlank
  @Column(name = "province", nullable = false)
  private String province;

  @Column(name = "temperature", nullable = false)
  private Integer temperature;

  @NotBlank
  @Column(name = "weather_description", nullable = false)
  private String weatherDescription;
}
