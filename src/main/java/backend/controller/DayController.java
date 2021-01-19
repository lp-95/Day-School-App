package backend.controller;

import backend.dto.DayDto;
import backend.exception.NotFoundException;
import backend.model.Day;
import backend.service.DayServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping( path = "/day" )
public class DayController {
    @Autowired
    private DayServiceImpl dayService;

    @GetMapping( path = "/{id}" )
    public Day getById( UUID id ) throws NotFoundException {
        return this.dayService.getById( id );
    }

    @PostMapping( path = "/save",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE )
    public Day save( @RequestBody DayDto dto ) throws NotFoundException {
        return this.dayService.save( dto );
    }

    @PutMapping( path = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public Day update( @PathVariable UUID id, @RequestBody DayDto dto ) throws NotFoundException {
        return this.dayService.update( id, dto );
    }

    @DeleteMapping( path = "/delete/{id}" )
    public void delete( @PathVariable UUID id ) throws NotFoundException {
        this.dayService.delete( id );
    }
}
