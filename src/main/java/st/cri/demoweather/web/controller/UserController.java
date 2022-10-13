package st.cri.demoweather.web.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import st.cri.demoweather.dto.UserDto;
import st.cri.demoweather.dto.mapper.UserMapper;
import st.cri.demoweather.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The User controller.
 */
@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  /**
   * Instantiates a new User controller.
   *
   * @param userService the user service
   * @param userMapper  the user mapper
   */
  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  /**
   * Gets all users.
   *
   * @return list of all users
   */
  @GetMapping("/all")
  @PreAuthorize("hasAuthority('ADMIN')")
  public List<UserDto> getAll() {
    return userService.getAll().stream().map(userMapper::toUserDto).collect(Collectors.toList());
  }

  /**
   * Gets user by id.
   *
   * @param userId the user id
   * @return the user
   */
  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public UserDto getById(@PathVariable("id") Integer userId) {
    return userMapper.toUserDto(userService.getById(userId));
  }

  /**
   * Save new user.
   *
   * @param userDto the user dto
   * @return the new user dto
   */
  @PostMapping("/save")
  @PreAuthorize("hasAuthority('ADMIN')")
  public UserDto save(@RequestBody UserDto userDto) {
    return userMapper.toUserDto(
        userService.saveNew(
            userMapper.toUser(userDto)));
  }

  /**
   * Update user.
   *
   * @param userDto the user dto
   * @return the user
   */
  @PutMapping("/update")
  @PostAuthorize("hasAuthority('ADMIN')")
  public UserDto update(@RequestBody UserDto userDto) {
    return userMapper.toUserDto(
        userService.update(
            userMapper.toUser(userDto)));
  }

  /**
   * Delete User.
   *
   * @param userId the user id
   * @return true if deleted.
   */
  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public boolean delete(@PathVariable("id") Integer userId) {
    return userService.delete(userId);
  }
}
