package backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Accounting {
    @Id
    private UUID id;
    @Column
    @Temporal( TemporalType.DATE )
    private Date dateFrom;
    @Column
    @Temporal( TemporalType.DATE )
    private Date dateTo;
    @OneToMany( mappedBy = "accounting", fetch = FetchType.LAZY )
    private List<Bill> bills = new ArrayList<>();
}
