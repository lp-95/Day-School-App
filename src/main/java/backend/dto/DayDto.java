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
@EqualsAndHashCode

public class DayDto {
    @JsonFormat( pattern = "dd-mm-yyyy" )
    private Date date;
    private Double hours;
    private UUID user;
}
