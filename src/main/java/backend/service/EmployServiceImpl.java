package backend.service;

import backend.dto.EmployDto;
import backend.exception.NotFoundException;
import backend.model.Day;
import backend.model.Employ;
import backend.repository.EmployRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static backend.exception.ErrorMessages.*;

@AllArgsConstructor
@Service
public class EmployServiceImpl implements EmployService {
    @Autowired
    private EmployRepository employRepository;
    @Autowired
    private DayServiceImpl dayService;

    @Override
    public Employ getById( UUID id ) throws NotFoundException {
        return this.employRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( ID_NOT_FOUND.getErrorMessage() ));
    }

    @Override
    public Set<Employ> getEmploys( int page, int size ) {
        return this.employRepository.findAll( PageRequest.of( page, size ) ).toSet();
    }

    @Override
    public Set<Employ> getByName( String name, int page, int size ) {
        return this.employRepository.findByName( name, PageRequest.of( page, size ) );
    }

    @Override
    public Employ save( Employ employ ) {
        return this.employRepository.save( employ );
    }

    @Override
    public Employ save( EmployDto dto ) {
        Employ employ = new Employ();
        employ.setFirstName( dto.getFirstName() );
        employ.setLastName( dto.getLastName() );
        employ.setEmail( dto.getEmail() );
        employ.setPhone( dto.getPhone() );
        employ.setRate( dto.getRate() );
        employ.setFullTime( dto.getFullTime() );
        return this.save( employ );
    }

    @Override
    public Employ update( UUID id, EmployDto dto ) throws NotFoundException {
        Employ employ = this.getById( id );
        employ.setFirstName( dto.getFirstName() );
        employ.setLastName( dto.getLastName() );
        employ.setEmail( dto.getEmail() );
        employ.setPhone( dto.getPhone() );
        employ.setRate( dto.getRate() );
        employ.setFullTime( dto.getFullTime() );
//        if ( dto.getDay() != null ) {
//            Day wrong = this.dayService.getById( id );
//            Double hours = wrong.getUserHours().get( employ );
//            wrong.getUserHours().remove( employ );
//            this.dayService.save( wrong );
//            Day correct = this.dayService.findByDate( dto.getDate() );
//            if ( correct != null ) {
//                correct.getUserHours().put( employ, hours );
//            } else {
//                correct = new Day();
//                correct.setDate( dto.getDate() );
//                Map<Employ,Double> map = new HashMap<>();
//                correct.setUserHours( map );
//            }
//            this.dayService.save( correct );
//        }
        return this.save( employ );
    }

    @Override
    public void delete( UUID id ) throws NotFoundException {
        Employ employ = this.getById( id );
        this.employRepository.delete( employ );
    }
}
