package com.DevJavaMinh.service;

import com.DevJavaMinh.dto.SeatDto;
import com.DevJavaMinh.dto.TrainDto;
import com.DevJavaMinh.model.Coach;

import java.util.List;

public interface SeatService {

    SeatDto getSeatById(Long id);
    SeatDto getSeatBySeatNumber(int seatNumber);
    List<SeatDto> getAllSeats();
    SeatDto addSeat(SeatDto seatDto);
    SeatDto updateSeat(Long id,SeatDto seatDto);
    void deleteSeat(Long id);
    Boolean checkSeat(Long id);

}
