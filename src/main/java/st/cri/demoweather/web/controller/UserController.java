package st.cri.demoweather.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import st.cri.demoweather.dto.UserDto;
import st.cri.demoweather.dto.mapper.UserMapper;
import st.cri.demoweather.model.UserRole;
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
        if (!isAdmin()) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        return userService.getAll().stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") Integer userId) {
        if (!isAdmin()) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        return userMapper.toUserDto(userService.getById(userId));
    }

    @PostMapping("/save")
    public UserDto save(@RequestBody UserDto userDto) {
        if (!isAdmin()) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        return userMapper.toUserDto(
                userService.saveNew(
                        userMapper.toUser(userDto)));
    }

    @PutMapping("/update")
    public UserDto update(@RequestBody UserDto userDto) {
        if (!isAdmin()) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        return userMapper.toUserDto(
                userService.update(
                        userMapper.toUser(userDto)));
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Integer userId) {
        if (!isAdmin()) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        return userService.delete(userId);
    }

    private boolean isAdmin() {
        return userService.findByNickname(
                ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                        .getUsername()).map(user ->
                user.getRole().equals(UserRole.ADMIN))
                .orElse(false);
    }
}
