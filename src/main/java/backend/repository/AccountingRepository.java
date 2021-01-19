package backend.repository;

import backend.model.Accounting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountingRepository extends JpaRepository<Accounting, UUID> {
}
