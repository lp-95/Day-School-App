package backend.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode( callSuper = false )

public class StudentDto extends UserDto {
    private Integer grade;
    private Boolean meal;
}
