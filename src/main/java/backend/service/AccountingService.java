package backend.service;

import backend.dto.AccountingDto;
import backend.exception.NotFoundException;
import backend.model.Accounting;

import java.util.List;
import java.util.UUID;

public interface AccountingService {
    Accounting getById( UUID id ) throws NotFoundException;
    List<Accounting> getAccounting( int page, int size );
    Accounting save( AccountingDto dto );
    Accounting update( UUID id, AccountingDto dto ) throws NotFoundException;
    void delete( UUID id ) throws NotFoundException;
}
