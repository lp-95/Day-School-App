package backend.service;

import backend.dto.AppUserDto;
import backend.exception.BadRequestException;
import backend.exception.ConflictException;
import backend.exception.NotFoundException;
import backend.model.AppUser;
import backend.repository.AppUserRepository;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static backend.exception.ErrorMessages.*;

@AllArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername( String email ) throws UsernameNotFoundException {
        return this.appUserRepository.findByEmail( email ).orElseThrow( () -> new UsernameNotFoundException(
                        String.format( USERNAME_NOT_FOUND.getErrorMessage(), email ) ));
    }

    @Override
    public AppUser getById(UUID id) throws NotFoundException {
        return this.appUserRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( ID_NOT_FOUND.getErrorMessage() ) );
    }

    @Override
    public AppUser save( AppUserDto appUserDto) throws BadRequestException, ConflictException {
        if ( !emailValid( appUserDto.getEmail() ) )
            throw new BadRequestException( String.format(
                    EMAIL_NOT_VALID.getErrorMessage(), appUserDto.getEmail() ) );
        if ( !passwordConfirmed( appUserDto.getEmail(), appUserDto.getConfirmPassword() ) )
            throw new BadRequestException(
                    PASSWORD_NOT_CONFIRMED.getErrorMessage() );
        if ( emailExist( appUserDto.getEmail() ) )
            throw new ConflictException( String.format(
                    EMAIL_ALREADY_EXIST.getErrorMessage(), appUserDto.getEmail() ) );
        AppUser appUser = new AppUser();
        appUser.setFirstName( appUserDto.getFirstName() );
        appUser.setLastName( appUserDto.getLastName() );
        appUser.setEmail( appUserDto.getEmail() );
        appUser.setPassword( this.passwordEncoder.encode( appUserDto.getPassword() ) );
        appUser.setPhone( appUserDto.getPhone() );
        return this.appUserRepository.save( appUser );
    }

    @Override
    public AppUser update( UUID id, AppUserDto request ) throws NotFoundException {
        AppUser appUser = this.getById( id );
        appUser.setFirstName( request.getFirstName() );
        appUser.setLastName( request.getLastName() );
        appUser.setPhone( request.getPhone() );
        return this.appUserRepository.save( appUser );
    }

    @Override
    public void delete( UUID id ) throws NotFoundException {
        this.appUserRepository.delete( this.getById( id ) );
    }

    private boolean emailValid( String email ) {
        return true;
    }

    private boolean emailExist( String email ) {
        return this.appUserRepository.findByEmail( email ).isPresent();
    }

    private boolean passwordConfirmed( String email, String confirmed ) {
        return email.equals( confirmed );
    }
}
