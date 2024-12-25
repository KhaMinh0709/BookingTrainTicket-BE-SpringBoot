package com.DevJavaMinh.mapper;

import com.DevJavaMinh.dto.TrainDto;
import com.DevJavaMinh.model.Train;

public class TrainMapping {
    public static Train maptoTrain(TrainDto trainDto){
        return new Train(
                trainDto.getTrainID(),
                trainDto.getTrainName(),
                trainDto.getCapacityTrain()
        );
    }

    public static TrainDto maptoTrainDto(Train train){
        return new TrainDto(
                train.getTrainID(),
                train.getTrainName(),
                train.getCapacityTrain()
        );
    }
}
