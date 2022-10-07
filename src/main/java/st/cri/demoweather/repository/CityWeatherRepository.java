package st.cri.demoweather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import st.cri.demoweather.model.CityWeather;

import java.util.Optional;

public interface CityWeatherRepository extends JpaRepository<CityWeather, Integer> {
    Optional<CityWeather> findByNameAndProvince(String name, String province);
}
