package backend.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public abstract class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private Long phone;
}
