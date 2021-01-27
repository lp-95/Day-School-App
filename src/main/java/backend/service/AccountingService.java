package backend.service;

import backend.dto.AccountingDto;
import backend.exception.NotFoundException;
import backend.model.Accounting;

import java.util.UUID;

public interface AccountingService {
    Accounting getById( UUID id ) throws NotFoundException;
    Accounting save( AccountingDto dto );
}
