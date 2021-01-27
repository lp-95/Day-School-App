package backend.controller;

import backend.dto.AppUserDto;
import backend.exception.BadRequestException;
import backend.exception.ConflictException;
import backend.exception.NotFoundException;
import backend.model.AppUser;
import backend.service.AppUserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping
public class AppUserController {
    private final AppUserServiceImpl appUserService;

    @GetMapping( path = "/{id}" )
    public AppUser getById( @PathVariable UUID id ) throws NotFoundException {
        return this.appUserService.getById( id );
    }

    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE,
                  produces = MediaType.APPLICATION_JSON_VALUE )
    public AppUser save( @RequestBody AppUserDto appUserDto) throws BadRequestException, ConflictException {
        return this.appUserService.save(appUserDto);
    }

    @PutMapping( path = "/{id}",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE  )
    public AppUser update( @PathVariable UUID id, @RequestBody AppUserDto appUserDto) throws NotFoundException {
        return this.appUserService.update( id, appUserDto);
    }

    @DeleteMapping( path = "/{id}" )
    public void delete( UUID id ) throws NotFoundException {
        this.appUserService.delete( id );
    }
}