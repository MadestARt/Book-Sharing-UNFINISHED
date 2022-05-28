package dto;


import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
public class LoginUserDto {
    String email;
    String password;
}
