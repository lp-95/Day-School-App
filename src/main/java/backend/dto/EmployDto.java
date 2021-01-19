package backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode( callSuper = false )
public class EmployDto extends UserDto {
    private Double rate;
    private Boolean fullTime;
    private UUID day;
    @JsonFormat( pattern = "dd-mm-yyyy" )
    private Date date;
}
