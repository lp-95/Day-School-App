package backend.controller;

import backend.dto.BillDto;
import backend.exception.NotFoundException;
import backend.model.Bill;
import backend.service.BillServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping( path = "/bill" )
public class BillController {
    private final BillServiceImpl billService;

    @GetMapping( path = "/{id}" )
    public Bill getById( @PathVariable UUID id ) throws NotFoundException {
        return this.billService.getById( id );
    }

    @PutMapping( path = "/{id}",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE )
    public Bill update( @PathVariable UUID id, @RequestBody BillDto dto ) throws NotFoundException {
        return this.billService.update( id, dto );
    }

    @DeleteMapping
    public void delete( @PathVariable UUID id ) throws NotFoundException {
        this.billService.delete( id );
    }
}