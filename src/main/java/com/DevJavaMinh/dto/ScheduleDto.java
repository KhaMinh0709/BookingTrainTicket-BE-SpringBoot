package com.DevJavaMinh.dto;

import com.DevJavaMinh.model.Train;
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
    private List<Long> trainID;
    private Date departureTime;
    private Date arrivalTime;
    private String departureStation;
    private String arrivalStation;
    private double price;

}
