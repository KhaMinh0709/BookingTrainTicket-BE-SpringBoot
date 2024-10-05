package com.DevJavaMinh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeatDto {
    private Long seatID;
    private Long coachID;
    private int seatNumber;
    private  Boolean isBooking;

}
