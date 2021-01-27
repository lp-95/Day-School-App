package backend.service;

import backend.dto.DayDto;
import backend.exception.NotFoundException;
import backend.model.Day;
import backend.model.Employ;
import backend.model.DayWork;
import backend.repository.DayRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static backend.exception.ErrorMessages.*;

@AllArgsConstructor
@Service
public class DayServiceImpl implements DayService {
    private final DayRepository dayRepository;
    private final EmployServiceImpl employService;

    @Override
    public Day getById( UUID id ) throws NotFoundException {
        return this.dayRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( ID_NOT_FOUND.getErrorMessage() ));
    }

    @Override
    public Day save( Day day ) {
        return this.dayRepository.save( day );
    }

    @Override
    public Day save( DayDto dto ) throws NotFoundException {
        Employ employ = this.employService.getById( dto.getUser() );
        Day day = this.dayRepository.getByDate( dto.getDate() ).orElse( new Day() );
        DayWork dayWork = new DayWork();
        dayWork.setDay( day );
        dayWork.setHours( dto.getHours() );
        dayWork.setEmploy( employ );
        day.getWorkingHours().add(dayWork);
        return this.save( day );
    }

    @Override
    public Day update( UUID id, DayDto dto ) throws NotFoundException {
        Day day = this.getById( id );
        day.setDate( dto.getDate() );
        return this.dayRepository.save( day );
    }

    @Override
    public void delete( UUID id ) throws NotFoundException {
        this.dayRepository.delete( this.getById( id ) );
    }
}
