package backend.service;

import backend.dto.AppUserDto;
import backend.exception.BadRequestException;
import backend.exception.ConflictException;
import backend.exception.NotFoundException;
import backend.model.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface AppUserService extends UserDetailsService {
    AppUser getById( UUID id ) throws NotFoundException;
    AppUser save( AppUserDto appUserDto) throws BadRequestException, ConflictException;
    AppUser update( UUID id, AppUserDto request ) throws NotFoundException;
    void delete( UUID id ) throws NotFoundException;
}
