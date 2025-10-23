package com.DevJavaMinh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleTrainDto {
    private Long id;
    private Long scheduleId;
    private String departureStation;
    private String arrivalStation;
    private Long trainId;
    private String trainName;
    private String trainNumber;
    private Date departureTime;
    private Date arrivalTime;
    private double price;
    private int availableSeats;
    private List<CoachDto> coaches; // Danh sách các toa tàu
}
