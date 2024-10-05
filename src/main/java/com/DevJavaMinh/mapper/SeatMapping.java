package com.DevJavaMinh.mapper;

import com.DevJavaMinh.dto.SeatDto;
import com.DevJavaMinh.model.Coach;
import com.DevJavaMinh.model.Seat;

public class SeatMapping {
    public static SeatDto mapToSeatDto(Seat seat) {
        if (seat == null) {
            return null;
        }
        return new SeatDto(
                seat.getSeatID(),
                seat.getCoach().getCoachID(),
                seat.getSeatNumber(),
                seat.getIsBooking()
        );
    }

    public static Seat mapToSeat(SeatDto seatDto) {
        if (seatDto == null) {
            return null;
        }
        Seat seat = new Seat();
        seat.setSeatID(seatDto.getSeatID());
        seat.setSeatNumber(seatDto.getSeatNumber());
        seat.setIsBooking(seatDto.getIsBooking());
        return seat;
    }

}
