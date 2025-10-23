package com.DevJavaMinh.service.imp;

import com.DevJavaMinh.dto.ScheduleTrainDto;
import com.DevJavaMinh.dto.ScheduleTrainBatchDto;
import com.DevJavaMinh.mapper.ScheduleTrainMapping;
import com.DevJavaMinh.model.Schedule;
import com.DevJavaMinh.model.ScheduleTrain;
import com.DevJavaMinh.model.Train;
import com.DevJavaMinh.repository.ScheduleRepository;
import com.DevJavaMinh.repository.ScheduleTrainRepository;
import com.DevJavaMinh.repository.TrainRepository;
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

    @Autowired
    private ScheduleTrainMapping scheduleTrainMapping;
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @Autowired
    private TrainRepository trainRepository;

    @Override
    public List<ScheduleTrainDto> getTrainsByScheduleCriteria(String departureStation, String arrivalStation, Date departureDate) {
        List<ScheduleTrain> scheduleTrains = scheduleTrainRepository.findTrainsByDateAndStations(departureStation, arrivalStation, departureDate);
        return scheduleTrains.stream()
                .map(scheduleTrainMapping::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleTrainDto> getTrainsByStations(String departureStation, String arrivalStation) {
        List<ScheduleTrain> scheduleTrains = scheduleTrainRepository.findTrainsByStations(departureStation, arrivalStation);
        return scheduleTrains.stream()
                .map(scheduleTrainMapping::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleTrainDto> getAllScheduleTrains() {
        List<ScheduleTrain> scheduleTrains = scheduleTrainRepository.findAll();
        return scheduleTrains.stream()
                .map(scheduleTrainMapping::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleTrainDto getScheduleTrainById(Long id) {
        ScheduleTrain scheduleTrain = scheduleTrainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ScheduleTrain not found with id: " + id));
        return scheduleTrainMapping.toDto(scheduleTrain);
    }

    @Override
    public ScheduleTrainDto createScheduleTrain(ScheduleTrainDto scheduleTrainDto) {
        ScheduleTrain scheduleTrain = new ScheduleTrain();
        
        // Tìm Schedule và Train theo ID
        Schedule schedule = scheduleRepository.findById(scheduleTrainDto.getScheduleId())
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + scheduleTrainDto.getScheduleId()));
        Train train = trainRepository.findById(scheduleTrainDto.getTrainId())
                .orElseThrow(() -> new RuntimeException("Train not found with id: " + scheduleTrainDto.getTrainId()));
        
        scheduleTrain.setSchedule(schedule);
        scheduleTrain.setTrain(train);
        scheduleTrain.setDepartureTime(scheduleTrainDto.getDepartureTime());
        scheduleTrain.setArrivalTime(scheduleTrainDto.getArrivalTime());
        scheduleTrain.setPrice(scheduleTrainDto.getPrice());
        
        ScheduleTrain savedScheduleTrain = scheduleTrainRepository.save(scheduleTrain);
        return scheduleTrainMapping.toDto(savedScheduleTrain);
    }

    @Override
    public List<ScheduleTrainDto> createScheduleTrainsBatch(ScheduleTrainBatchDto batchDto) {
        // Tìm Schedule theo ID
        Schedule schedule = scheduleRepository.findById(batchDto.getScheduleId())
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + batchDto.getScheduleId()));
        
        List<ScheduleTrainDto> createdScheduleTrains = new java.util.ArrayList<>();
        
        // Tạo ScheduleTrain cho từng Train với thời gian riêng
        for (ScheduleTrainBatchDto.TrainScheduleInfo trainSchedule : batchDto.getTrainSchedules()) {
            Train train = trainRepository.findById(trainSchedule.getTrainId())
                    .orElseThrow(() -> new RuntimeException("Train not found with id: " + trainSchedule.getTrainId()));
            
            ScheduleTrain scheduleTrain = new ScheduleTrain();
            scheduleTrain.setSchedule(schedule);
            scheduleTrain.setTrain(train);
            scheduleTrain.setDepartureTime(trainSchedule.getDepartureTime());
            scheduleTrain.setArrivalTime(trainSchedule.getArrivalTime());
            scheduleTrain.setPrice(trainSchedule.getPrice());
            
            ScheduleTrain savedScheduleTrain = scheduleTrainRepository.save(scheduleTrain);
            createdScheduleTrains.add(scheduleTrainMapping.toDto(savedScheduleTrain));
        }
        
        return createdScheduleTrains;
    }

    @Override
    public ScheduleTrainDto updateScheduleTrain(Long id, ScheduleTrainDto scheduleTrainDto) {
        ScheduleTrain scheduleTrain = scheduleTrainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ScheduleTrain not found with id: " + id));
        
        // Cập nhật thông tin
        Schedule schedule = scheduleRepository.findById(scheduleTrainDto.getScheduleId())
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + scheduleTrainDto.getScheduleId()));
        Train train = trainRepository.findById(scheduleTrainDto.getTrainId())
                .orElseThrow(() -> new RuntimeException("Train not found with id: " + scheduleTrainDto.getTrainId()));
        
        scheduleTrain.setSchedule(schedule);
        scheduleTrain.setTrain(train);
        scheduleTrain.setDepartureTime(scheduleTrainDto.getDepartureTime());
        scheduleTrain.setArrivalTime(scheduleTrainDto.getArrivalTime());
        scheduleTrain.setPrice(scheduleTrainDto.getPrice());
        
        ScheduleTrain updatedScheduleTrain = scheduleTrainRepository.save(scheduleTrain);
        return scheduleTrainMapping.toDto(updatedScheduleTrain);
    }

    @Override
    public void deleteScheduleTrain(Long id) {
        if (!scheduleTrainRepository.existsById(id)) {
            throw new RuntimeException("ScheduleTrain not found with id: " + id);
        }
        scheduleTrainRepository.deleteById(id);
    }
}
