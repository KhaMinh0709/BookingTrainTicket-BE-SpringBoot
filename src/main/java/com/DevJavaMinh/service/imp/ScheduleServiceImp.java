package com.DevJavaMinh.service.imp;

import com.DevJavaMinh.dto.ScheduleDto;
import com.DevJavaMinh.dto.TrainDto;
import com.DevJavaMinh.exception.NotFoundException;
import com.DevJavaMinh.mapper.ScheduleMapping;
import com.DevJavaMinh.mapper.TrainMapping;
import com.DevJavaMinh.model.Schedule;

import com.DevJavaMinh.model.ScheduleTrain;
import com.DevJavaMinh.model.Train;
import com.DevJavaMinh.repository.ScheduleRepository;
import com.DevJavaMinh.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImp implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public List<ScheduleDto> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(ScheduleMapping::mapScheduleDto)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleDto getScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Schedule not found"));
        return ScheduleMapping.mapScheduleDto(schedule);
    }

    @Override
    public ScheduleDto create(ScheduleDto scheduleDto) {
        // Chuyển đổi từ DTO sang entity
        Schedule schedule = ScheduleMapping.mapSchedule(scheduleDto);

        // Lưu lịch trình và các ScheduleTrain kèm theo
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // Chuyển đổi kết quả đã lưu sang DTO và trả về
        return ScheduleMapping.mapScheduleDto(savedSchedule);
    }



    @Override
    public ScheduleDto update(Long id, ScheduleDto scheduleDto) {
        // Tìm lịch trình hiện tại
        Schedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Schedule not found"));

        // Chuyển đổi List<ScheduleTrainDto> thành List<ScheduleTrain>
        List<ScheduleTrain> scheduleTrains = scheduleDto.getScheduleTrains().stream()
                .map(dto -> {
                    ScheduleTrain scheduleTrain = new ScheduleTrain();
                    Train train = new Train();
                    train.setTrainID(dto.getTrainID()); // Chỉ định ID của tàu
                    scheduleTrain.setTrain(train); // Liên kết với đối tượng Train
                    scheduleTrain.setDepartureTime(dto.getDepartureTime());
                    scheduleTrain.setArrivalTime(dto.getArrivalTime());
                    scheduleTrain.setPrice(dto.getPrice());
                    scheduleTrain.setSchedule(existingSchedule); // Liên kết với đối tượng Schedule
                    return scheduleTrain;
                }).collect(Collectors.toList());

        // Cập nhật lại danh sách ScheduleTrain trong Schedule
        existingSchedule.setScheduleTrains(scheduleTrains);

        // Lưu lại thay đổi
        Schedule updatedSchedule = scheduleRepository.save(existingSchedule);

        // Trả về DTO sau khi cập nhật
        return ScheduleMapping.mapScheduleDto(updatedSchedule);
    }



    @Override
    public void deleteScheduleById(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new NotFoundException("Schedule not found");
        }
        scheduleRepository.deleteById(id);
    }

    @Override
    public List<TrainDto> findTrainsInScheDuleOneWay(String departureStation, String arrivalStation, Date departureTime) {
        List<Train> listTrain = scheduleRepository.findTrainsByScheduleAndDepartureDate(
                departureStation, arrivalStation, departureTime);
        return listTrain.stream()
                .map(TrainMapping::maptoTrainDto)
                .collect(Collectors.toList());
    }







}
