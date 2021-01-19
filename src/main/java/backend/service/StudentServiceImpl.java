package backend.service;

import backend.dto.StudentDto;
import backend.exception.NotFoundException;
import backend.model.Student;
import backend.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

import static backend.exception.ErrorMessages.ID_NOT_FOUND;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private static final Double TUITION = 10000.;
    private static final Double MEAL = 3500.;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student getById( UUID id ) throws NotFoundException {
        return this.studentRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( ID_NOT_FOUND.getErrorMessage()));
    }

    @Override
    public Set<Student> getStudents( int page, int size ) {
        return this.studentRepository.findAll( PageRequest.of( page, size ) ).toSet();
    }

    @Override
    public Set<Student> getByName( String name, int page, int size ) {
        return this.studentRepository.findByName( name, PageRequest.of( page, size) );
    }

    @Override
    public Student save( Student student ) {
        return this.studentRepository.save( student );
    }

    @Override
    public Student save( StudentDto dto ) {
        Student student = new Student();
        student.setFirstName( dto.getFirstName() );
        student.setLastName( dto.getLastName() );
        student.setEmail( dto.getEmail() );
        student.setPhone( dto.getPhone() );
        student.setGrade( dto.getGrade() );
        student.setMeal( dto.getMeal() );
        if (student.getMeal()) {
            student.setAmount( MEAL + TUITION );
        } else {
            student.setAmount( TUITION );
        }
        return this.save( student );
    }

    @Override
    public Student update( UUID id, StudentDto dto ) throws NotFoundException {
        Student student = this.getById( id );
        student.setFirstName( dto.getFirstName() );
        student.setLastName( dto.getLastName() );
        student.setEmail( dto.getEmail() );
        student.setPhone( dto.getPhone() );
        student.setGrade( dto.getGrade() );
        student.setMeal( dto.getMeal() );
        return this.save( student );
    }

    @Override
    public void delete( UUID id ) throws NotFoundException {
        Student student = this.getById( id );
         this.studentRepository.delete( student );
    }

}
