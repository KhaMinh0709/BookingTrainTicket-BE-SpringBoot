package com.DevJavaMinh.controller;

import com.DevJavaMinh.dto.CoachDto;
import com.DevJavaMinh.dto.SeatDto;
import com.DevJavaMinh.service.CoachService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/coach")
@AllArgsConstructor
public class CoachController {

    private final CoachService coachService;

    @GetMapping()
    public ResponseEntity<List<CoachDto>> findAll() {
        List<CoachDto> coaches = coachService.getAllCoaches();
        return ResponseEntity.ok(coaches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoachDto> findById(@PathVariable Long id) {
        CoachDto coach = coachService.getCoachById(id);
        return ResponseEntity.ok(coach);
    }

    @PostMapping
    public ResponseEntity<CoachDto> createCoach(@RequestBody CoachDto coachDto) {
        CoachDto createdCoach = coachService.addCoach(coachDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCoach);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CoachDto> updateCoach(@PathVariable Long id, @RequestBody CoachDto coachDto) {
        CoachDto updatedCoach = coachService.updateCoachById(id, coachDto);
        return ResponseEntity.ok(updatedCoach);
    }


    //lấy list toa theo 1 tàu
    @GetMapping("/train/{trainID}")
    public ResponseEntity<List<SeatDto>> getAllSeatsByCoach(@PathVariable Long trainID) {

        List<CoachDto> list = coachService.getCoachByTrainId(trainID);
        return new ResponseEntity(list, HttpStatus.OK);



    }

}
