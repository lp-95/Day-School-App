package backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode( callSuper = false )
@Entity
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public class Student extends User {
    @Column
    private Integer grade;
    @Column
    private Boolean meal;
    @Column
    private Double amount;
    @OneToMany( mappedBy = "student", fetch = FetchType.LAZY )
    private List<Bill> bills = new ArrayList<>();
}
