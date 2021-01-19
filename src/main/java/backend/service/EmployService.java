package backend.service;

import backend.dto.EmployDto;
import backend.exception.NotFoundException;
import backend.model.Employ;

import java.util.Set;
import java.util.UUID;

public interface EmployService {
    Employ getById( UUID id ) throws NotFoundException;
    Set<Employ> getEmploys( int page, int size );
    Set<Employ> getByName( String name, int page, int size );
    Employ save( Employ employ );
    Employ save( EmployDto dto );
    Employ update( UUID id, EmployDto dto ) throws NotFoundException;
    void delete( UUID id ) throws NotFoundException;
}
