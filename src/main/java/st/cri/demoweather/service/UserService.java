package st.cri.demoweather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import st.cri.demoweather.repository.UserRepository;
import st.cri.demoweather.model.User;
import st.cri.demoweather.model.UserRole;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer userId) {
        return getUser(userId).orElse(null);
    }

    public User saveNew(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return getUser(user.getId()).map(updatedUser -> {
            updatedUser.setNickname(user.getNickname());
            updatedUser.setRole(user.getRole());
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
}
