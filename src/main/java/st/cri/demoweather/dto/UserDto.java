package st.cri.demoweather.dto;

import lombok.Data;

/**
 * The User dto.
 */
@Data
public class UserDto {

  private Integer userId;
  private String nickname;
  private String role;
  private String email;
  private String password;
}
