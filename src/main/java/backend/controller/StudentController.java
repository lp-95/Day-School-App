package backend.controller;

import backend.dto.StudentDto;
import backend.exception.NotFoundException;
import backend.model.Student;
import backend.service.StudentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping( path = "/student" )
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping( path = "/{id}" )
    public Student getEmploy(@PathVariable UUID id ) throws NotFoundException {
        return this.studentService.getById( id );
    }

    @PostMapping( path = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public Student save( @RequestBody StudentDto dto ) {
        return this.studentService.save( dto );
    }

    @PutMapping( path = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public Student update( @PathVariable UUID id, @RequestBody StudentDto dto ) throws NotFoundException {
        return this.studentService.update( id, dto );
    }

    @DeleteMapping( path = "/delete/{id}" )
    public void delete( @PathVariable UUID id ) throws NotFoundException {
        this.studentService.delete( id );
    }
}
