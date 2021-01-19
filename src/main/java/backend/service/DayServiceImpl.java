package backend.service;

import backend.dto.DayDto;
import backend.exception.NotFoundException;
import backend.model.Day;
import backend.model.Employ;
import backend.repository.DayRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static backend.exception.ErrorMessages.*;

@AllArgsConstructor
@Service
public class DayServiceImpl implements DayService {

    @Autowired
    private DayRepository dayRepository;
    @Autowired
    private EmployServiceImpl employService;

    @Override
    public Day getById( UUID id ) throws NotFoundException {
        return this.dayRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( ID_NOT_FOUND.getErrorMessage() ));
    }

    @Override
    public Day findByDate( Date date ) {
        return this.dayRepository.getByDate( date );
    }

    @Override
    public Day save( Day day ) {
        return this.dayRepository.save( day );
    }

    @Override
    public Day save( DayDto dto ) throws NotFoundException {
        Day day = this.findByDate( dto.getDate() );
        Employ employ = this.employService.getById( dto.getUser() );
//        if ( day == null ) {
//            Map<Employ, Double> map = new HashMap<>();
//            map.put( employ, dto.getHours() );
//            day = new Day();
//            day.setDate( dto.getDate() );
//            day.setUserHours( map );
//        } else {
//            day.getUserHours().put( employ, dto.getHours() );
//        }
//        employ.getDays().add( day );
        this.employService.save( employ );
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
        this.dayRepository.delete( this.getById( id ));
    }
}
