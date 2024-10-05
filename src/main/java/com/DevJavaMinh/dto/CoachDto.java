package com.DevJavaMinh.dto;

import com.DevJavaMinh.model.Train;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CoachDto {
    private Long coachID;
    private String typeCoach;
    private Long trainId;
    private List<Long> seatList;
}
