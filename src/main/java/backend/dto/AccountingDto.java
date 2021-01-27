package backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AccountingDto {
    @JsonFormat( pattern = "dd-mm-yyyy" )
    private Date from;
    @JsonFormat( pattern = "dd-mm-yyyy" )
    private Date to;
}
