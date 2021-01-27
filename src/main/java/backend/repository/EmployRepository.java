package backend.repository;

import backend.model.Employ;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface EmployRepository extends JpaRepository<Employ, UUID> {
}
