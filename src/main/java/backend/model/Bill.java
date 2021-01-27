package backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
public abstract class Bill {
    @Id
    private UUID id;
    @Column
    private Double amount;
    @Column
    private Boolean paid;
    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "accounting_id" )
    private Accounting accounting;
}
