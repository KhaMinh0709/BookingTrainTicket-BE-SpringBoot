package com.DevJavaMinh.controller;

import com.DevJavaMinh.dto.ScheduleDto;
import com.DevJavaMinh.dto.TrainDto;
import com.DevJavaMinh.service.ScheduleTrainService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/schedule-trains")
@AllArgsConstructor
public class ScheduleTrainController {

    @Autowired
    private ScheduleTrainService scheduleTrainService;

    @GetMapping("/search")
    public ResponseEntity<List<TrainDto>> getTrainsBySchedule(@RequestParam String departureStation,
                                                              @RequestParam String arrivalStation,
                                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureTime) {
        return new ResponseEntity<>(scheduleTrainService.getTrainsByScheduleCriteria
                (departureStation, arrivalStation, departureTime),
                HttpStatus.OK);
    }
}

