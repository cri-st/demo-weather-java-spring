package st.cri.demoweather.controller;

import org.springframework.web.bind.annotation.*;
import st.cri.demoweather.dto.UserDto;
import st.cri.demoweather.dto.mapper.UserMapper;
import st.cri.demoweather.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/all")
    public List<UserDto> getAll() {
        return userService.getAll().stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") Integer userId) {
        return userMapper.toUserDto(userService.getById(userId));
    }

    @PostMapping("/save")
    public UserDto save(@RequestBody UserDto userDto) {
        return userMapper.toUserDto(
                userService.saveNew(
                        userMapper.toUser(userDto)));
    }

    @PutMapping("/update")
    public UserDto update(@RequestBody UserDto userDto) {
        return userMapper.toUserDto(
                userService.update(
                        userMapper.toUser(userDto)));
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Integer userId) {
        return userService.delete(userId);
    }
}
