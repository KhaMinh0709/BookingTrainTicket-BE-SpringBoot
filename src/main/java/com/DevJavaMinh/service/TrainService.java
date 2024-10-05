package com.DevJavaMinh.service;

import com.DevJavaMinh.dto.TrainDto;

import java.util.List;

public interface TrainService {
    List<TrainDto> getAllTrain();
    TrainDto getTrainById(Long id);
    TrainDto addTrain(TrainDto train);
    TrainDto updateTrainByID(Long id, TrainDto train);
    void deleteTrainByTD(Long id);
}
