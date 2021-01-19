package backend.repository;

import backend.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface DayRepository extends JpaRepository<Day, UUID> {
    Day getByDate( Date date );
}
