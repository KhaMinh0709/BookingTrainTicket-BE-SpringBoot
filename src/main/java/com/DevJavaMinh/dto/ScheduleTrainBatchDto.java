package com.DevJavaMinh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleTrainBatchDto {
    private Long scheduleId;
    private List<TrainScheduleInfo> trainSchedules;
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TrainScheduleInfo {
        private Long trainId;
        private Date departureTime;
        private Date arrivalTime;
        private double price;
    }
}
