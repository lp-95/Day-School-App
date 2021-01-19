package backend.service;

import backend.dto.BillDto;
import backend.exception.NotFoundException;
import backend.model.Bill;

import java.util.Set;
import java.util.UUID;

public interface BillService {
    Bill getById( UUID id ) throws NotFoundException;
    Set<Bill> getBills( int page, int size );
    Bill save( Bill bill );
    Bill save( BillDto dto );
    Bill update( UUID id, BillDto dto ) throws NotFoundException;
    void delete( UUID id ) throws NotFoundException;
}
