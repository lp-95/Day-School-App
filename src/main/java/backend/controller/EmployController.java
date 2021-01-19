package backend.controller;

import backend.dto.EmployDto;
import backend.exception.NotFoundException;
import backend.model.Employ;
import backend.service.EmployServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping( path = "/employ" )
public class EmployController {
    @Autowired
    private EmployServiceImpl employService;

    @GetMapping( path = "/{id}" )
    public Employ getEmploy( @PathVariable UUID id ) throws NotFoundException {
        return this.employService.getById( id );
    }

    @PostMapping( path = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public Employ save( @RequestBody EmployDto dto ) {
        return this.employService.save( dto );
    }

    @PutMapping( path = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public Employ update( @PathVariable UUID id, @RequestBody EmployDto dto ) throws NotFoundException {
        return this.employService.update( id, dto );
    }

    @DeleteMapping( path = "/delete/{id}" )
    public void delete( @PathVariable UUID id ) throws NotFoundException {
        this.employService.delete( id );
    }
}
