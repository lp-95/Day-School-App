package backend.controller;

import backend.dto.AccountingDto;
import backend.exception.NotFoundException;
import backend.model.Accounting;
import backend.service.AccountingServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping( path="/accounting" )
public class AccountingController {
    private final AccountingServiceImpl accountingService;

    @GetMapping( path = "/{id}" )
    public Accounting getById( @PathVariable UUID id ) throws NotFoundException {
        return this.accountingService.getById( id );
    }

    @GetMapping
    public List<Accounting> getAccounting( @RequestParam int page, @RequestParam int size  ) {
        return this.accountingService.getAccounting( page, size );
    }

    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE,
                  produces = MediaType.APPLICATION_JSON_VALUE )
    public Accounting save( @RequestBody AccountingDto dto ) {
        return accountingService.save( dto );
    }

    @PutMapping( path =  "/{id}",
                 consumes =  MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE )
    public Accounting update( @PathVariable UUID id, AccountingDto dto ) throws NotFoundException {
        return this.accountingService.getById( id );
    }

    @DeleteMapping( path = "{/id}" )
    public void delete( UUID id ) throws NotFoundException {
        this.accountingService.delete( id );
    }
}
