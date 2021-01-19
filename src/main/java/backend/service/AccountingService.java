package backend.service;

import backend.dto.AccountingDto;
import backend.exception.BadRequestException;
import backend.exception.NotFoundException;
import backend.model.Accounting;

import java.util.Set;
import java.util.UUID;

public interface AccountingService {
    Accounting getById( UUID id ) throws NotFoundException;
    Set<Accounting> getAllAccounting( int page, int size );
    Accounting save( Accounting accounting );
    Accounting save( AccountingDto dto ) throws BadRequestException;
    Accounting update( UUID id, AccountingDto dto ) throws NotFoundException;
    void delete( UUID id ) throws NotFoundException;
}
