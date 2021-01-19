package backend.model;

import lombok.*;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity

public class Accounting {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    @Temporal( TemporalType.DATE )
    private Date startDate;
    @Column
    @Temporal( TemporalType.DATE )
    private Date endDate;
    @OneToMany( mappedBy = "accounting", fetch = FetchType.LAZY )
    private Set<Bill> bills;
}
