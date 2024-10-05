package com.DevJavaMinh.service;

import com.DevJavaMinh.dto.CoachDto;

import java.util.List;

public interface CoachService {
    List<CoachDto>   getAllCoaches();
    CoachDto getCoachById(Long id);
    CoachDto addCoach(CoachDto coach);
    CoachDto updateCoachById(Long id,CoachDto coach);
    List<Long> getSeatsByCoachId(Long id);
}
