package com.DevJavaMinh.mapper;

import com.DevJavaMinh.dto.CoachDto;
import com.DevJavaMinh.model.Coach;
import com.DevJavaMinh.model.Seat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoachMapping {
    public Coach mapToCoach(CoachDto coachDto) {
        Coach coach = new Coach();

        coach.setCoachID(coachDto.getCoachID());
        coach.setCoachNumber(coachDto.getCoachNumber());
        coach.setTypeCoach(coachDto.getTypeCoach());

        return coach;
    }
    
    public CoachDto toDto(Coach coach) {
        CoachDto coachDto = new CoachDto();

        coachDto.setCoachID(coach.getCoachID());
        coachDto.setTypeCoach(coach.getTypeCoach());
        coachDto.setTrainId(coach.getTrain().getTrainID());
        coachDto.setCoachNumber(coach.getCoachNumber());
        
        if (coach.getSeats() != null) {
            List<Long> listSeat = coach.getSeats().stream().map(Seat::getSeatID).toList();
            coachDto.setSeatList(listSeat);
        }

        return coachDto;
    }
}
