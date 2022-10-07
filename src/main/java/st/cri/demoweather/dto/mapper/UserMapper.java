package st.cri.demoweather.dto.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import st.cri.demoweather.dto.UserDto;
import st.cri.demoweather.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "nickname", target = "nickname"),
            @Mapping(source = "role", target = "role"),
            @Mapping(target = "id", source = "userId"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "password", source = "password")
    })
    User toUser(UserDto userDto);

    @InheritInverseConfiguration
    @Mapping(target = "password", ignore = true)
    UserDto toUserDto(User user);
}
