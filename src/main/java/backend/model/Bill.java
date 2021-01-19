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
public class Bill {
    @Id
    private UUID id;
    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "student_id", insertable = false, updatable = false )
    private Student student;
    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "employ_id", insertable = false, updatable = false )
    private Employ employ;
    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "accounting_id" )
    private Accounting accounting;
    @Column
    private Double amount;
    @Column
    private Boolean paid;
}
