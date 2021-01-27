package backend.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode( callSuper = false )
@Getter
@Setter
public class AppUserDto extends UserDto {
    private String password;
    private String confirmPassword;
}
