package backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Day {
    @Id
    @GeneratedValue
    private UUID id;
    @Temporal( TemporalType.DATE )
    private Date date;
    @OneToMany( mappedBy = "day", fetch = FetchType.LAZY )
    private List<DayWork> workingHours;
}
