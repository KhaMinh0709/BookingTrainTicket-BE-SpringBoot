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
        List<ScheduleTrain> scheduleTrains = scheduleDto.getScheduleTrains().stream()
                .map(dto -> {
                    ScheduleTrain scheduleTrain = new ScheduleTrain();
                    Train train = new Train();
                    train.setTrainID(dto.getTrainID()); // Chỉ định ID của tàu
                    scheduleTrain.setTrain(train);
                    scheduleTrain.setDepartureTime(dto.getDepartureTime());
                    scheduleTrain.setArrivalTime(dto.getArrivalTime());
                    scheduleTrain.setPrice(dto.getPrice());
                    scheduleTrain.setSchedule(schedule); // Liên kết với lịch trình hiện tại
                    return scheduleTrain;
                }).collect(Collectors.toList());
        schedule.setScheduleTrains(scheduleTrains);
        return schedule;
    }

    public static ScheduleDto mapScheduleDto(Schedule schedule) {
        List<ScheduleDto.ScheduleTrainDto> scheduleTrainDtos = schedule.getScheduleTrains().stream()
                .map(st -> new ScheduleDto.ScheduleTrainDto(
                        st.getTrain().getTrainID(),
                        st.getTrain().getTrainName(),
                        st.getTrain().getCapacityTrain(),
                        st.getDepartureTime(),
                        st.getArrivalTime(),
                        st.getPrice()
                )).collect(Collectors.toList());

        return new ScheduleDto(schedule.getScheduleID(),schedule.getDepartureStation(),schedule.getArrivalStation(),
                scheduleTrainDtos);
    }
}
