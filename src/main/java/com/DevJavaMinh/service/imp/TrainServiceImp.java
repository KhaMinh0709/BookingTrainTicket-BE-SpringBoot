package com.DevJavaMinh.service.imp;

import com.DevJavaMinh.dto.TrainDto;
import com.DevJavaMinh.exception.NotFoundException;
import com.DevJavaMinh.mapper.TrainMapping;
import com.DevJavaMinh.model.Train;
import com.DevJavaMinh.repository.TrainRepository;
import com.DevJavaMinh.service.TrainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainServiceImp implements TrainService {
    private final TrainRepository trainRepository;
    @Override
    public List<TrainDto> getAllTrain() {
        List<Train> list = trainRepository.findAll();
        return list.stream().map(TrainMapping::maptoTrainDto).toList();
    }

    @Override
    public TrainDto getTrainById(Long id) {
        Train train = trainRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("not found train"));
        return TrainMapping.maptoTrainDto(train);
    }

    @Override
    public TrainDto addTrain(TrainDto train) {
        Train trainSave = trainRepository.save(TrainMapping.maptoTrain(train));
        return TrainMapping.maptoTrainDto(trainSave);
    }

    @Override
    public TrainDto updateTrainByID(Long id, TrainDto trainDto) {
        Train train = trainRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Not found train"));

        train.setTrainName(trainDto.getTrainName());
        train.setCapacityTrain(trainDto.getCapacityTrain());

        Train savedTrain = trainRepository.save(train);
        return TrainMapping.maptoTrainDto(savedTrain);
    }

    @Override
    public void deleteTrainByTD(Long id) {
        Train train = trainRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Not found train"));

        trainRepository.delete(train);
    }
}
