package com.DevJavaMinh.service;

import com.DevJavaMinh.dto.ScheduleTrainDto;
import com.DevJavaMinh.dto.ScheduleTrainBatchDto;

import java.util.Date;
import java.util.List;

public interface ScheduleTrainService {
    List<ScheduleTrainDto> getTrainsByScheduleCriteria(String departureStation, String arrivalStation, Date departureDate);
    List<ScheduleTrainDto> getTrainsByStations(String departureStation, String arrivalStation);
    List<ScheduleTrainDto> getAllScheduleTrains();
    ScheduleTrainDto getScheduleTrainById(Long id);
    ScheduleTrainDto createScheduleTrain(ScheduleTrainDto scheduleTrainDto);
    List<ScheduleTrainDto> createScheduleTrainsBatch(ScheduleTrainBatchDto batchDto);
    ScheduleTrainDto updateScheduleTrain(Long id, ScheduleTrainDto scheduleTrainDto);
    void deleteScheduleTrain(Long id);
}
