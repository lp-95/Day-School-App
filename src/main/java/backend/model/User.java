package backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public abstract class User {
    @Id
    @GeneratedValue
    private UUID id;
    @Column( name = "first_name" )
    private String firstName;
    @Column( name = "last_name" )
    private String lastName;
    @Column( unique = true )
    private String email;
    @Column( unique = true )
    private Long phone;
}
