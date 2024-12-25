package com.DevJavaMinh.mapper;

import com.DevJavaMinh.dto.ScheduleDto;
import com.DevJavaMinh.model.Schedule;
import com.DevJavaMinh.model.ScheduleTrain;
import com.DevJavaMinh.model.Train;

import java.util.List;
import java.util.stream.Collectors;

public class ScheduleMapping {
    public static Schedule mapSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = new Schedule();
        schedule.setScheduleID(scheduleDto.getScheduleID());
        schedule.setArrivalStation(scheduleDto.getArrivalStation());
        schedule.setDepartureStation(scheduleDto.getDepartureStation());
        return schedule;
    }

    public static ScheduleDto mapScheduleDto(Schedule schedule) {

        return new ScheduleDto(schedule.getScheduleID(),schedule.getDepartureStation(),schedule.getArrivalStation());
    }
}
