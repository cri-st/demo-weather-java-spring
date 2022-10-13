package st.cri.demoweather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import st.cri.demoweather.model.CityWeather;

import java.util.Optional;

/**
 * The City weather repository.
 */
public interface CityWeatherRepository extends JpaRepository<CityWeather, Integer> {

  /**
   * Find by name and province.
   *
   * @param name     the name
   * @param province the province
   * @return the optional City Weather
   */
  Optional<CityWeather> findByNameAndProvince(String name, String province);
}
