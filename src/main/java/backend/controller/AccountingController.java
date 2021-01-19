package backend.controller;

import backend.dto.AccountingDto;
import backend.exception.BadRequestException;
import backend.exception.NotFoundException;
import backend.model.Accounting;
import backend.service.AccountingServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping( path = "/accounting" )
public class AccountingController {
    @Autowired
    private AccountingServiceImpl accountingService;

    @GetMapping( path = "/{id}" )
    public Accounting getAccounting( @PathVariable UUID id ) throws NotFoundException {
        return this.accountingService.getById( id );
    }

    @PostMapping( path = "/save",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE )
    public Accounting save( @RequestBody AccountingDto dto ) throws BadRequestException {
        return this.accountingService.save( dto );
    }

    @PutMapping( path = "/update/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE )
    public Accounting update( @PathVariable UUID id, @RequestBody AccountingDto dto ) throws NotFoundException {
        return this.accountingService.update( id, dto );
    }

    @DeleteMapping( path = "/delete/{id}")
    public void delete( @PathVariable UUID id ) throws NotFoundException {
        this.accountingService.delete( id );
    }
}
