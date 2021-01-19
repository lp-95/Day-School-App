package backend.service;

import backend.dto.StudentDto;
import backend.exception.NotFoundException;
import backend.model.Student;

import java.util.Set;
import java.util.UUID;

public interface StudentService {
    Student getById( UUID id ) throws NotFoundException;
    Set<Student> getStudents( int page, int size );
    Set<Student> getByName( String name, int page, int size );
    Student save( Student student );
    Student save( StudentDto dto );
    Student update( UUID id, StudentDto dto )throws NotFoundException;
    void delete( UUID id ) throws NotFoundException;
}
