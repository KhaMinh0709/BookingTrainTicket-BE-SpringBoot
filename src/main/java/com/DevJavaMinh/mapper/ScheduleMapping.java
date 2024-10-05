package com.DevJavaMinh.mapper;

import com.DevJavaMinh.dto.ScheduleDto;
import com.DevJavaMinh.model.Schedule;
import com.DevJavaMinh.model.Train;

import java.util.List;
import java.util.stream.Collectors;

public class ScheduleMapping {
    public static Schedule mapSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = new Schedule();
        schedule.setScheduleID(scheduleDto.getScheduleID());
        schedule.setDepartureTime(scheduleDto.getDepartureTime());
        schedule.setArrivalTime(scheduleDto.getArrivalTime());
        schedule.setDepartureStation(scheduleDto.getDepartureStation());
        schedule.setArrivalStation(scheduleDto.getArrivalStation());
        schedule.setPrice(scheduleDto.getPrice());
        return schedule;
    }

    public static ScheduleDto mapScheduleDto(Schedule schedule) {
        // Lấy danh sách trainID từ danh sách các đối tượng Train
        List<Long> trainIDs = schedule.getTrains().stream()
                .map(Train::getTrainID)
                .collect(Collectors.toList());

        return new ScheduleDto(
                schedule.getScheduleID(),
                trainIDs,
                schedule.getDepartureTime(),
                schedule.getArrivalTime(),
                schedule.getDepartureStation(),
                schedule.getArrivalStation(),
                schedule.getPrice()
        );
    }
}
