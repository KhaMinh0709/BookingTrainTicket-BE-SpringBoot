package com.DevJavaMinh.controller;

import com.DevJavaMinh.dto.ScheduleTrainDto;
import com.DevJavaMinh.dto.ScheduleTrainBatchDto;
import com.DevJavaMinh.service.ScheduleTrainService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/schedule-trains")
@AllArgsConstructor
public class ScheduleTrainController {

    @Autowired
    private ScheduleTrainService scheduleTrainService;

    @GetMapping("/search")
    public ResponseEntity<List<ScheduleTrainDto>> getTrainsBySchedule(
            @RequestParam String departureStation,
            @RequestParam String arrivalStation,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate) {
        
        List<ScheduleTrainDto> trains;
        
        if (departureDate != null) {
            // Tìm theo ga đi, ga đến và ngày đi
            trains = scheduleTrainService.getTrainsByScheduleCriteria(departureStation, arrivalStation, departureDate);
        } else {
            return ResponseEntity.badRequest().body(null);
            // Tìm theo ga đi và ga đến (không theo ngày)
            // trains = scheduleTrainService.getTrainsByStations(departureStation, arrivalStation);
        }
        
        return new ResponseEntity<>(trains, HttpStatus.OK);
    }

    // Lấy tất cả lịch trình tàu
    @GetMapping
    public ResponseEntity<List<ScheduleTrainDto>> getAllScheduleTrains() {
        List<ScheduleTrainDto> scheduleTrains = scheduleTrainService.getAllScheduleTrains();
        return ResponseEntity.ok(scheduleTrains);
    }

    // Lấy lịch trình tàu theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleTrainDto> getScheduleTrainById(@PathVariable Long id) {
        ScheduleTrainDto scheduleTrain = scheduleTrainService.getScheduleTrainById(id);
        return ResponseEntity.ok(scheduleTrain);
    }

    // Tạo lịch trình tàu mới
    @PostMapping
    public ResponseEntity<ScheduleTrainDto> createScheduleTrain(@RequestBody ScheduleTrainDto scheduleTrainDto) {
        ScheduleTrainDto createdScheduleTrain = scheduleTrainService.createScheduleTrain(scheduleTrainDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdScheduleTrain);
    }

    // Tạo nhiều lịch trình tàu cùng lúc (1 lịch trình có nhiều tàu)
    @PostMapping("/batch")
    public ResponseEntity<List<ScheduleTrainDto>> createScheduleTrainsBatch(@RequestBody ScheduleTrainBatchDto batchDto) {
        List<ScheduleTrainDto> createdScheduleTrains = scheduleTrainService.createScheduleTrainsBatch(batchDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdScheduleTrains);
    }

    // Cập nhật lịch trình tàu
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleTrainDto> updateScheduleTrain(@PathVariable Long id, @RequestBody ScheduleTrainDto scheduleTrainDto) {
        ScheduleTrainDto updatedScheduleTrain = scheduleTrainService.updateScheduleTrain(id, scheduleTrainDto);
        return ResponseEntity.ok(updatedScheduleTrain);
    }

    // Xóa lịch trình tàu
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduleTrain(@PathVariable Long id) {
        scheduleTrainService.deleteScheduleTrain(id);
        return ResponseEntity.noContent().build();
    }
}

