package st.cri.demoweather.service;

import org.springframework.stereotype.Service;
import st.cri.demoweather.repository.UserRepository;
import st.cri.demoweather.model.User;
import st.cri.demoweather.web.security.PasswordEncoder;

import java.util.List;
import java.util.Optional;

/**
 * The User service.
 */
@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  /**
   * Instantiates a new User service.
   *
   * @param userRepository  the user repository
   * @param passwordEncoder the password encoder
   */
  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Gets all users.
   *
   * @return the all
   */
  public List<User> getAll() {
    return userRepository.findAll();
  }

  /**
   * Gets a user by id.
   *
   * @param userId the user id
   * @return the user by id
   */
  public User getById(Integer userId) {
    return getUser(userId).orElse(null);
  }

  /**
   * Save new user.
   *
   * @param user the user
   * @return the new user
   */
  public User saveNew(User user) {
    user.setPassword(passwordEncoder.encoder().encode(user.getPassword()));
    return userRepository.save(user);
  }

  /**
   * Update a user.
   *
   * @param user the user
   * @return the user updated
   */
  public User update(User user) {
    return getUser(user.getId()).map(updatedUser -> {
        if (!user.getNickname().equals(updatedUser.getNickname())) {
            updatedUser.setNickname(user.getNickname());
        }
        if (!user.getRole().equals(updatedUser.getRole())) {
            updatedUser.setRole(user.getRole());
        }
        if (!user.getEmail().equals(updatedUser.getEmail())) {
            updatedUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty() && !user.getPassword()
            .isBlank()) {
            updatedUser.setPassword(passwordEncoder.encoder().encode(user.getPassword()));
        }
      return userRepository.save(updatedUser);
    }).orElse(null);
  }

  /**
   * Delete user.
   *
   * @param userId the user id
   * @return true if deleted.
   */
  public boolean delete(Integer userId) {
    return getUser(userId).map(user -> {
      userRepository.delete(user);
      return true;
    }).orElse(false);
  }

  private Optional<User> getUser(int userId) {
    return userRepository.findById(userId);
  }

  /**
   * Find by nickname.
   *
   * @param nickname the nickname
   * @return the optional User
   */
  public Optional<User> findByNickname(String nickname) {
    return userRepository.findByNickname(nickname);
  }
}
