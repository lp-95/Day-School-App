package backend.service;

import backend.dto.AccountingDto;
import backend.exception.BadRequestException;
import backend.exception.NotFoundException;
import backend.model.Accounting;
import backend.model.Bill;
import backend.repository.AccountingRepository;
import backend.repository.EmployRepository;
import backend.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import static backend.exception.ErrorMessages.*;

@AllArgsConstructor
@Service
public class AccountingServiceImpl implements AccountingService {
    @Autowired
    private AccountingRepository accountingRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EmployRepository employRepository;

    @Override
    public Accounting getById( UUID id ) throws NotFoundException {
        return this.accountingRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( ID_NOT_FOUND.getErrorMessage() ));
    }

    @Override
    public Set<Accounting> getAllAccounting( int page, int size ) {
        return this.accountingRepository.findAll( PageRequest.of( page, size ) ).toSet();
    }

    @Override
    public Accounting save( Accounting accounting ) {
        return this.accountingRepository.save( accounting );
    }

    @Override
    public Accounting save( AccountingDto dto ) throws BadRequestException {
        Accounting accounting = new Accounting();
        if ( dto.getFrom().after( dto.getTo() ) )
            throw new BadRequestException( INCORRECT_DATE.getErrorMessage() );
        accounting.setStartDate( dto.getFrom() );
        accounting.setEndDate( dto.getTo() );
        this.studentRepository.findAll().forEach( student -> student.getBills().add( new Bill(
                UUID.randomUUID(),
                student,
                null,
                accounting,
                student.getAmount(),
                false
        ) ) );
        this.employRepository.findAll().forEach( employ -> employ.getBills().add( new Bill(
                UUID.randomUUID(),
                null,
                employ,
                accounting,
                employ.getRate() * this.getWorkDays( accounting.getStartDate(), accounting.getEndDate() ),
                false
        ) ) );
        return this.save( accounting );
    }

    @Override
    public Accounting update( UUID id, AccountingDto dto ) throws NotFoundException {
        Accounting accounting = this.getById( id );
        accounting.setStartDate( dto.getFrom() );
        accounting.setEndDate( dto.getTo() );
        return this.save( accounting );
    }

    @Override
    public void delete( UUID id ) throws NotFoundException {
        this.accountingRepository.delete( this.getById( id ) );
    }

    private Integer getWorkDays( Date from, Date to ) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime( from );
        Calendar endCal = Calendar.getInstance();
        endCal.setTime( to );
        int workDays = 0;
        if ( startCal.getTimeInMillis() > endCal.getTimeInMillis() ) {
            startCal.setTime( to );
            endCal.setTime( from );
        }
        while ( startCal.getTimeInMillis() <= endCal.getTimeInMillis() ) {
            if ( startCal.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY &&
                    startCal.get(Calendar.DAY_OF_WEEK ) != Calendar.SATURDAY ) {
                workDays++;
            }
            startCal.add( Calendar.DAY_OF_MONTH, 1 );
        }
        return workDays;
    }
}
