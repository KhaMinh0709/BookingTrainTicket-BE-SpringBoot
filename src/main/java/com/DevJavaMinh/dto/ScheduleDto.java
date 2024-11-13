package com.DevJavaMinh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleDto {
    private Long scheduleID;
    private String departureStation;
    private String arrivalStation;
    private List<ScheduleTrainDto> scheduleTrains;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ScheduleTrainDto {
        private Long trainID;
        private String trainName;
        private int capacityTrain;
        private Date departureTime;
        private Date arrivalTime;
        private double price;
    }
}


