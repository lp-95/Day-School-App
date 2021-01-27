package backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode( callSuper = false )
@Entity
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public class Employ extends User {
    @Column
    private Double rate;
    @Column
    private Boolean fullTime;
    @OneToMany( mappedBy = "employ", fetch = FetchType.LAZY )
    private List<EmployBill> bills = new ArrayList<>();
    @OneToMany( mappedBy = "employ", fetch = FetchType.LAZY )
    private List<DayWork> dayWork = new ArrayList<>();
}
