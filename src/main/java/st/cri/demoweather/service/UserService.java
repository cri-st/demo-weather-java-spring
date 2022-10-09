package st.cri.demoweather.service;

import org.springframework.stereotype.Service;
import st.cri.demoweather.repository.UserRepository;
import st.cri.demoweather.model.User;
import st.cri.demoweather.web.security.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer userId) {
        return getUser(userId).orElse(null);
    }

    public User saveNew(User user) {
        user.setPassword(passwordEncoder.encoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(User user) {
        return getUser(user.getId()).map(updatedUser -> {
            if (!user.getNickname().equals(updatedUser.getNickname())) updatedUser.setNickname(user.getNickname());
            if (!user.getRole().equals(updatedUser.getRole())) updatedUser.setRole(user.getRole());
            if (!user.getEmail().equals(updatedUser.getEmail())) updatedUser.setEmail(user.getEmail());
            if (user.getPassword() != null && !user.getPassword().isEmpty() && !user.getPassword().isBlank()) updatedUser.setPassword(passwordEncoder.encoder().encode(user.getPassword()));
            return userRepository.save(updatedUser);
        }).orElse(null);
    }

    public boolean delete(Integer userId) {
        return getUser(userId).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }

    private Optional<User> getUser(int userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }
}
