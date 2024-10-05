package com.DevJavaMinh.service.imp;

import com.DevJavaMinh.dto.ScheduleDto;
import com.DevJavaMinh.exception.NotFoundException;
import com.DevJavaMinh.mapper.ScheduleMapping;
import com.DevJavaMinh.model.Schedule;
import com.DevJavaMinh.model.Train;
import com.DevJavaMinh.repository.ScheduleRepository;
import com.DevJavaMinh.repository.TrainRepository;
import com.DevJavaMinh.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImp implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private TrainRepository trainRepository; // Để lấy Train từ repository

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
        // Chuyển đổi ScheduleDto sang Schedule
        Schedule schedule = ScheduleMapping.mapSchedule(scheduleDto);

        // Lưu lịch trình vào cơ sở dữ liệu
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // Cập nhật bảng liên kết schedule_train
        if (scheduleDto.getTrainID() != null && !scheduleDto.getTrainID().isEmpty()) {
            List<Train> trains = trainRepository.findAllById(scheduleDto.getTrainID());
            savedSchedule.setTrains(trains);
        }else{
            new NotFoundException("Schedule not found");
        }

        // Lưu lại lịch trình đã cập nhật với danh sách tàu
        Schedule updatedSchedule = scheduleRepository.save(savedSchedule);

        // Trả về DTO sau khi tạo
        return ScheduleMapping.mapScheduleDto(updatedSchedule);
    }



    @Override
    public ScheduleDto update(Long id, ScheduleDto scheduleDto) {
        // Tìm lịch trình hiện tại
        Schedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Schedule not found"));

        // Cập nhật các trường cần thiết
        existingSchedule.setArrivalStation(scheduleDto.getArrivalStation());
        existingSchedule.setDepartureStation(scheduleDto.getDepartureStation());
        existingSchedule.setPrice(scheduleDto.getPrice());
        existingSchedule.setDepartureTime(scheduleDto.getDepartureTime());
        existingSchedule.setArrivalTime(scheduleDto.getArrivalTime());

        // Nếu cần cập nhật tàu, lấy danh sách tàu mới từ DTO
        List<Train> trains = trainRepository.findAllById(scheduleDto.getTrainID());
        if (!trains.isEmpty()) {
            existingSchedule.setTrains(trains);
        }

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
    public List<ScheduleDto> searchSchedules(String departureStation, String arrivalStation, Date departureTime) {
        List<Schedule> schedules = scheduleRepository.findAll();  // Lấy tất cả lịch trình
        return schedules.stream()
                .filter(schedule -> schedule.getDepartureStation().equalsIgnoreCase(departureStation))
                .filter(schedule -> schedule.getArrivalStation().equalsIgnoreCase(arrivalStation))
                .filter(schedule -> {
                    // So sánh ngày đi không tính giờ
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(schedule.getDepartureTime());
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    Calendar searchCalendar = Calendar.getInstance();
                    searchCalendar.setTime(departureTime);
                    searchCalendar.set(Calendar.HOUR_OF_DAY, 0);
                    searchCalendar.set(Calendar.MINUTE, 0);
                    searchCalendar.set(Calendar.SECOND, 0);
                    searchCalendar.set(Calendar.MILLISECOND, 0);

                    return calendar.getTime().equals(searchCalendar.getTime());
                })
                .map(ScheduleMapping::mapScheduleDto)
                .collect(Collectors.toList());
    }



}
