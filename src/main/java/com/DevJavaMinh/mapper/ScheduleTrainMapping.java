package com.DevJavaMinh.mapper;

import com.DevJavaMinh.dto.ScheduleTrainDto;
import com.DevJavaMinh.dto.CoachDto;
import com.DevJavaMinh.model.ScheduleTrain;
import com.DevJavaMinh.model.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleTrainMapping {
    
    @Autowired
    private CoachMapping coachMapping;
    
    public ScheduleTrainDto toDto(ScheduleTrain scheduleTrain) {
        if (scheduleTrain == null) {
            return null;
        }
        
        ScheduleTrainDto dto = new ScheduleTrainDto();
        dto.setId(scheduleTrain.getId());
        dto.setScheduleId(scheduleTrain.getSchedule().getScheduleID());
        dto.setDepartureStation(scheduleTrain.getSchedule().getDepartureStation());
        dto.setArrivalStation(scheduleTrain.getSchedule().getArrivalStation());
        dto.setTrainId(scheduleTrain.getTrain().getTrainID());
        dto.setTrainName(scheduleTrain.getTrain().getTrainName());
        dto.setTrainNumber(scheduleTrain.getTrain().getTrainID().toString()); // Sử dụng trainID làm trainNumber
        dto.setDepartureTime(scheduleTrain.getDepartureTime());
        dto.setArrivalTime(scheduleTrain.getArrivalTime());
        dto.setPrice(scheduleTrain.getPrice());
        
        // TODO: Tính số ghế trống (cần implement logic đếm ghế)
        dto.setAvailableSeats(50); // Tạm thời set 50
        
        // Lấy danh sách các toa tàu
        List<CoachDto> coaches = scheduleTrain.getTrain().getCoaches().stream()
                .map(coachMapping::toDto)
                .collect(Collectors.toList());
        dto.setCoaches(coaches);
        
        return dto;
    }
}
