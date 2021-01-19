package backend.dto;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class BillDto {
    private UUID user;
    private UUID accounting;
    private Double amount;
    private Boolean paid;
}
