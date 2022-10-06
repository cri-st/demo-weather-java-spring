package st.cri.demoweather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.cri.demoweather.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
