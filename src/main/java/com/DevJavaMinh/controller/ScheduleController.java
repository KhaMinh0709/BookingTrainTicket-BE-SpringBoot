package com.DevJavaMinh.controller;

import com.DevJavaMinh.dto.ScheduleDto;
import com.DevJavaMinh.dto.TrainDto;
import com.DevJavaMinh.service.ScheduleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("api/schedule")
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping()
    public ResponseEntity<List<ScheduleDto>> findAll() {
        List<ScheduleDto> list = scheduleService.getAllSchedules();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ScheduleDto> findById(@PathVariable("id") Long id) {
        ScheduleDto scheduleDto = scheduleService.getScheduleById(id);
        return new ResponseEntity<>(scheduleDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ScheduleDto> createSchedule(@RequestBody ScheduleDto scheduleDto) {
        return new ResponseEntity<>(scheduleService.create(scheduleDto), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ScheduleDto> update(@PathVariable("id") Long id,
                                              @RequestBody ScheduleDto scheduleDto) {
        return new ResponseEntity<>(scheduleService.update(id,scheduleDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        scheduleService.deleteScheduleById(id);
        return new ResponseEntity<>("Deleted Schedule", HttpStatus.OK);
    }


}

