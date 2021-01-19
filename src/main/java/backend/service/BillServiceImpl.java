package backend.service;

import backend.dto.BillDto;
import backend.exception.NotFoundException;
import backend.model.Bill;
import backend.repository.BillRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

import static backend.exception.ErrorMessages.ID_NOT_FOUND;

@AllArgsConstructor
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public Bill getById( UUID id ) throws NotFoundException {
        return this.billRepository.findById( id )
                .orElseThrow(() -> new NotFoundException( ID_NOT_FOUND.getErrorMessage() ));
    }

    @Override
    public Set<Bill> getBills( int page, int size ) {
        return this.billRepository.findAll( PageRequest.of( page, size ) ).toSet();
    }

    @Override
    public Bill save( Bill bill ) {
        return this.billRepository.save( bill );
    }

    @Override
    public Bill save( BillDto dto ) {
        Bill bill = new Bill();

        return this.save( bill );
    }

    @Override
    public Bill update( UUID id, BillDto dto ) throws NotFoundException {
        Bill bill = this.getById( id );
        bill.setPaid( dto.getPaid() );
        return this.save( bill );
    }

    @Override
    public void delete( UUID id ) throws NotFoundException {
        this.billRepository.delete( this.getById( id ) );
    }
}
