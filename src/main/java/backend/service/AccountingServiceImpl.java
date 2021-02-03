package backend.service;

import backend.dto.AccountingDto;
import backend.exception.NotFoundException;
import backend.model.*;
import backend.repository.AccountingRepository;
import backend.repository.EmployRepository;
import backend.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static backend.exception.ErrorMessages.ID_NOT_FOUND;

@AllArgsConstructor
@Service
public class AccountingServiceImpl implements AccountingService{
    private static final Double TUITION = 10000.;
    private static final Double MEAL = 3500.;
    private final AccountingRepository accountingRepository;
    private final EmployRepository employRepository;
    private final StudentRepository studentRepository;

    @Override
    public Accounting getById( UUID id ) throws NotFoundException {
        return this.accountingRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( ID_NOT_FOUND.getErrorMessage() ));
    }

    @Override
    public List<Accounting> getAccounting( int page, int size ) {
        return this.accountingRepository.findAll( PageRequest.of( page, size ) ).getContent();
    }

    @Override
    public Accounting save( AccountingDto dto ) {
        Accounting accounting = new Accounting();
        accounting.setDateFrom( dto.getFrom() );
        accounting.setDateTo( dto.getTo() );
        for ( Employ employ : this.employRepository.findAll() ) {
            generateBills( employ, accounting );
        }
        for ( Student student : this.studentRepository.findAll() ) {
            generateBills( student, accounting );
        }
        return this.accountingRepository.save( accounting );
    }

    @Override
    public Accounting update( UUID id, AccountingDto dto ) throws NotFoundException {
        Accounting accounting = this.getById( id );
        accounting.setDateFrom( dto.getFrom() );
        accounting.setDateTo( dto.getTo() );
        for ( Employ employ : this.employRepository.findAll() ) {
            generateBills( employ, accounting );
        }
        for ( Student student : this.studentRepository.findAll() ) {
            generateBills( student, accounting );
        }
        return this.accountingRepository.save( accounting );
    }

    @Override
    public void delete( UUID id ) throws NotFoundException {
        this.accountingRepository.delete( this.getById( id ) );
    }

    private void generateBills( Employ employ, Accounting accounting ) {
        EmployBill employBill = new EmployBill();
        employBill.setId( UUID.randomUUID() );
        employBill.setAmount( getEmployAmount( employ, accounting.getDateFrom(), accounting.getDateTo() ) );
        employBill.setAccounting( accounting );
        employBill.setEmploy( employ );
        employ.getBills().add( employBill );
        this.employRepository.save( employ );
    }

    private void generateBills( Student student, Accounting accounting ) {
        StudentBill studentBill = new StudentBill();
        studentBill.setId( UUID.randomUUID() );
        studentBill.setAmount( getStudentAmount( student ) );
        studentBill.setAccounting( accounting );
        studentBill.setStudent( student );
        student.getBills().add( studentBill );
        this.studentRepository.save( student );
    }

    private Double getStudentAmount( Student student ) {
        return student.getMeal() ? MEAL + TUITION : TUITION;
    }

    private Double getEmployAmount( Employ employ, Date from, Date to ) {
        if ( employ.getFullTime() ) {
            double totalHours = getWorkDays( from, to ) * 8;
            double nonWorkedHours = getHours( employ.getDayWork() );
            double toPay = totalHours - nonWorkedHours;
            return toPay * employ.getRate();
        } else {
            return employ.getRate() * getHours( employ.getDayWork() );
        }
    }

    private double getHours( List<DayWork> dayWork ) {
        double hours = 0.;
        for ( DayWork day : dayWork ) {
            hours += day.getHours();
        }
        return hours;
    }

    private int getWorkDays( Date startDate, Date endDate ) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime( endDate );
        int workDays = 0;
        if ( startCal.getTimeInMillis() > endCal.getTimeInMillis() ) {
            startCal.setTime( endDate );
            endCal.setTime( startDate );
        }
        while ( startCal.getTimeInMillis() <= endCal.getTimeInMillis() ) {
            if ( startCal.get(Calendar.DAY_OF_WEEK ) != Calendar.FRIDAY &&
                    startCal.get( Calendar.DAY_OF_WEEK ) != Calendar.SATURDAY ) {
                workDays++;
            }
            startCal.add( Calendar.DAY_OF_MONTH, 1 );
        }
        return workDays;
    }
}
