package com.DevJavaMinh.service;

import com.DevJavaMinh.dto.ScheduleDto;
import com.DevJavaMinh.dto.TrainDto;

import java.util.Date;
import java.util.List;

public interface ScheduleService {
    List<ScheduleDto> getAllSchedules();
    ScheduleDto getScheduleById(Long id);
    ScheduleDto create(ScheduleDto scheduleDto);
    ScheduleDto update(Long id,ScheduleDto scheduleDto);
    void deleteScheduleById(Long id);

    List<TrainDto> searchTrainInSchedules(String departureStation, String arrivalStation, Date departureTime);

}
