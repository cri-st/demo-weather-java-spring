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
            @Mapping(target = "id", source = "userId")
    })
    User toUser(UserDto userDto);

    @InheritInverseConfiguration
    UserDto toUserDto(User user);
}
