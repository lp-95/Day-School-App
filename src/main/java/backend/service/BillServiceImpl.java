package backend.service;

import backend.dto.BillDto;
import backend.exception.NotFoundException;
import backend.model.Bill;
import backend.repository.BillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static backend.exception.ErrorMessages.ID_NOT_FOUND;

@AllArgsConstructor
@Service
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;

    @Override
    public Bill getById( UUID id ) throws NotFoundException {
        return this.billRepository.findById( id )
                .orElseThrow(() -> new NotFoundException( ID_NOT_FOUND.getErrorMessage() ));
    }

    @Override
    public Bill update( UUID id, BillDto dto ) throws NotFoundException {
        Bill bill = this.getById( id );
        bill.setAmount( dto.getAmount() );
        bill.setPaid( dto.getPaid() );
        return this.billRepository.save( bill );
    }

    @Override
    public void delete( UUID id ) throws NotFoundException {
        this.billRepository.delete( this.getById( id ) );
    }
}
