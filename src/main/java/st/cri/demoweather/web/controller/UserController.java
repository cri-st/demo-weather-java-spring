package st.cri.demoweather.web.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> getAll() {
        return userService.getAll().stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDto getById(@PathVariable("id") Integer userId) {
        return userMapper.toUserDto(userService.getById(userId));
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDto save(@RequestBody UserDto userDto) {
        return userMapper.toUserDto(
                userService.saveNew(
                        userMapper.toUser(userDto)));
    }

    @PutMapping("/update")
    @PostAuthorize("hasAuthority('ADMIN')")
    public UserDto update(@RequestBody UserDto userDto) {
        return userMapper.toUserDto(
                userService.update(
                        userMapper.toUser(userDto)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean delete(@PathVariable("id") Integer userId) {
        return userService.delete(userId);
    }
}
