package st.cri.demoweather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.cri.demoweather.model.User;

import java.util.Optional;

/**
 * The User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  /**
   * Find by nickname.
   *
   * @param nickname the nickname
   * @return the optional User
   */
  Optional<User> findByNickname(String nickname);
}
