package com.DevJavaMinh.service.imp;

import com.DevJavaMinh.dto.SeatDto;
import com.DevJavaMinh.exception.NotFoundException;
import com.DevJavaMinh.mapper.SeatMapping;
import com.DevJavaMinh.model.Coach;
import com.DevJavaMinh.model.Seat;
import com.DevJavaMinh.repository.CoachRepository;
import com.DevJavaMinh.repository.SeatRepository;
import com.DevJavaMinh.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SeatServiceImp implements SeatService {

    private final SeatRepository seatRepository;
    private final CoachRepository coachRepository;

    @Override
    public SeatDto getSeatById(Long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Seat Not Found"));
        return SeatMapping.mapToSeatDto(seat);
    }

    @Override
    public SeatDto getSeatBySeatNumber(int seatNumber) {
        Seat seat = seatRepository.findBySeatNumber(seatNumber);
        return SeatMapping.mapToSeatDto(seat);
    }

    @Override
    public List<SeatDto> getAllSeats() {
        List<Seat> seats = seatRepository.findAll();
        return seats.stream().map(SeatMapping::mapToSeatDto).toList();
    }

    @Override
    public SeatDto addSeat(SeatDto seatDto) {
        Seat seat = SeatMapping.mapToSeat(seatDto);
        Optional<Coach> coach = coachRepository.findById(seatDto.getCoachID());
        if (coach.isPresent()) {
            seat.setCoach(coach.get());
        }else{
            new NotFoundException("Coach Not Found");
        }
        Seat seatSave = seatRepository.save(seat);
        return SeatMapping.mapToSeatDto(seatSave);
    }

    @Override
    public SeatDto updateSeat(Long id,SeatDto seatDto) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Seat Not Found"));
        seat.setSeatNumber(seatDto.getSeatNumber());
        seat.setIsBooking(seatDto.getIsBooking());
        seat.setSeatNumber(seatDto.getSeatNumber());
        seatRepository.save(seat);
        return SeatMapping.mapToSeatDto(seatRepository.save(seat));
    }

    @Override
    public void deleteSeat(Long id) {
        seatRepository.findById(id)
                        .orElseThrow(()-> new NotFoundException("Seat Not Found"));
        seatRepository.deleteById(id);

    }

    @Override
    public Boolean checkSeat(Long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Seat Not Found"));
        if (seat != null) {
            return seat.getIsBooking();
        }
        return false;
    }

    @Override
    public List<SeatDto> getSeatByCoach(Long coachID) {
        Optional<Coach> coach = coachRepository.findById(coachID);
        List<Seat> listSeatInCoach = seatRepository.findByCoach(coach.orElse(null));
        return listSeatInCoach.stream().map(SeatMapping::mapToSeatDto).toList();
    }

}
