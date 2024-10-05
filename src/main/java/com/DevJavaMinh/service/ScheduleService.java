package com.DevJavaMinh.service;

import com.DevJavaMinh.dto.ScheduleDto;

import java.util.Date;
import java.util.List;

public interface ScheduleService {
    List<ScheduleDto> getAllSchedules();
    ScheduleDto getScheduleById(Long id);
    ScheduleDto create(ScheduleDto scheduleDto);
    ScheduleDto update(Long id,ScheduleDto scheduleDto);
    void deleteScheduleById(Long id);

    List<ScheduleDto> searchSchedules(String departureStation, String arrivalStation, Date departureTime);

}
