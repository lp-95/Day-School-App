package backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;
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
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable( name = "user_day_mapping",
//            joinColumns = { @JoinColumn ( name = "day_id", referencedColumnName = "id" ) },
//            inverseJoinColumns = { @JoinColumn ( name = "user_id", referencedColumnName = "id" ) } )
//    private Map<Employ, Double> userHours;
}
