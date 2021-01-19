package backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    @ManyToMany
    @JoinColumn( name = "employ_id" )
    private Set<Day> days;
    @OneToMany( mappedBy = "employ", fetch = FetchType.LAZY )
    private List<Bill> bills = new ArrayList<>();
}
