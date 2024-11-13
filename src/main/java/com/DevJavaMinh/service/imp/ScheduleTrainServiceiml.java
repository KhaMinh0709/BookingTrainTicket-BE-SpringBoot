package com.DevJavaMinh.service.imp;

import com.DevJavaMinh.dto.ScheduleDto;
import com.DevJavaMinh.dto.TrainDto;
import com.DevJavaMinh.mapper.ScheduleMapping;
import com.DevJavaMinh.mapper.TrainMapping;
import com.DevJavaMinh.model.Schedule;
import com.DevJavaMinh.model.ScheduleTrain;
import com.DevJavaMinh.repository.ScheduleTrainRepository;
import com.DevJavaMinh.service.ScheduleTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleTrainServiceiml implements ScheduleTrainService {
    @Autowired
    private ScheduleTrainRepository scheduleTrainRepository;

    public List<ScheduleDto> getTrainsByScheduleCriteria(String departureStation, String arrivalStation, Date departureTime) {
         List<ScheduleTrain> list = scheduleTrainRepository.findTrainsByDateAndStations(departureStation, arrivalStation, departureTime);
    }
}
