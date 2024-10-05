package com.DevJavaMinh.mapper;

import com.DevJavaMinh.dto.CoachDto;
import com.DevJavaMinh.model.Coach;
import com.DevJavaMinh.model.Seat;

import java.util.List;

public class CoachMapping {
    public static Coach mapToCoach(CoachDto coachDto) {
        Coach coach = new Coach();

        coach.setCoachID(coachDto.getCoachID());
        coach.setTypeCoach(coachDto.getTypeCoach());

        return coach;
    }
    public static CoachDto mapToCoachDto(Coach coach) {
        CoachDto coachDto = new CoachDto();

        coachDto.setCoachID(coach.getCoachID());
        coachDto.setTypeCoach(coach.getTypeCoach());
        coachDto.setTrainId(coach.getTrain().getTrainID());
        List<Long> listSeat= coach.getSeats().stream().map(Seat::getSeatID).toList();
        coachDto.setSeatList(listSeat);

        return coachDto;
    }
}
