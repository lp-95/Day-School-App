package backend.service;

import backend.dto.DayDto;
import backend.exception.NotFoundException;
import backend.model.Day;

import java.util.Date;
import java.util.UUID;

public interface DayService {
    Day getById( UUID id ) throws NotFoundException;
    Day findByDate( Date date );
    Day save( Day day );
    Day save( DayDto dto ) throws NotFoundException ;
    Day update( UUID id, DayDto dto ) throws NotFoundException;
    void delete( UUID id ) throws NotFoundException;
}
