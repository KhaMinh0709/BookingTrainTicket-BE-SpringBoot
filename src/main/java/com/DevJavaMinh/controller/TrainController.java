package com.DevJavaMinh.controller;

import com.DevJavaMinh.dto.TrainDto;
import com.DevJavaMinh.model.Train;
import com.DevJavaMinh.service.TrainService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/train" )
@AllArgsConstructor
public class TrainController {
    private final TrainService trainService;

    @GetMapping()
    public ResponseEntity<List<TrainDto>> findAll() {
        List<TrainDto> list = trainService.getAllTrain();
        return new  ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TrainDto> findById(@PathVariable("id") Long id) {
        TrainDto trainDto = trainService.getTrainById(id);
        return new ResponseEntity<>(trainDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TrainDto> createTrain(@RequestBody TrainDto trainDto) {;
        return new ResponseEntity<>(trainService.addTrain(trainDto), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TrainDto> update(@PathVariable("id") Long id,
                                           @RequestBody TrainDto trainDto) {
        return new ResponseEntity<>(trainService.updateTrainByID(id,trainDto), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        trainService.deleteTrainByTD(id);
        return new ResponseEntity<>("Deleted Train", HttpStatus.OK);
    }
}
