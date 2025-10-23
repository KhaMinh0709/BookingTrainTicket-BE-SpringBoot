package com.DevJavaMinh.mapper;

import com.DevJavaMinh.dto.TrainDto;
import com.DevJavaMinh.model.Train;
import org.springframework.stereotype.Component;

@Component
public class TrainMapping {
    public Train maptoTrain(TrainDto trainDto){
        Train train = new Train();
        train.setTrainID(trainDto.getTrainID());
        train.setTrainName(trainDto.getTrainName());
        train.setCapacityTrain(trainDto.getCapacityTrain());
        return train;
    }

    public TrainDto maptoTrainDto(Train train){
        return new TrainDto(
                train.getTrainID(),
                train.getTrainName(),
                train.getCapacityTrain()
        );
    }
}
