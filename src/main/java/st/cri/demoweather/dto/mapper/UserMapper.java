package st.cri.demoweather.dto.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.cri.demoweather.dto.UserDto;
import st.cri.demoweather.model.User;

/**
 * The User mapper.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

  /**
   * User DTO to user.
   *
   * @param userDto the user dto
   * @return the user
   */
  @Mapping(source = "nickname", target = "nickname")
  @Mapping(source = "role", target = "role")
  @Mapping(target = "id", source = "userId")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "password", source = "password")
  User toUser(UserDto userDto);

  /**
   * User to user dto.
   *
   * @param user the user
   * @return the user dto
   */
  @InheritInverseConfiguration
  @Mapping(target = "password", ignore = true)
  UserDto toUserDto(User user);
}
