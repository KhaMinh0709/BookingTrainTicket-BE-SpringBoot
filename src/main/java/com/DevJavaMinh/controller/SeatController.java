package com.DevJavaMinh.controller;

import com.DevJavaMinh.dto.SeatDto;
import com.DevJavaMinh.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@AllArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/{id}")
    public ResponseEntity<SeatDto> getSeatById(@PathVariable Long id) {
        SeatDto seat = seatService.getSeatById(id);
        return seat != null ? ResponseEntity.ok(seat) : ResponseEntity.notFound().build();
    }

    @GetMapping("/number/{seatNumber}")
    public ResponseEntity<SeatDto> getSeatBySeatNumber(@PathVariable int seatNumber) {
        SeatDto seat = seatService.getSeatBySeatNumber(seatNumber);
        return seat != null ? ResponseEntity.ok(seat) : ResponseEntity.notFound().build();
    }

    // Thêm ghế
    @PostMapping
    public ResponseEntity<SeatDto> addSeat(@RequestBody SeatDto seatDto) {
        SeatDto createdSeat = seatService.addSeat(seatDto);
        return ResponseEntity.status(201).body(createdSeat);
    }

    // Cập nhật ghế
    @PutMapping("/{id}")
    public ResponseEntity<SeatDto> updateSeat(@PathVariable Long id,
                                              @RequestBody SeatDto seatDto) {
        SeatDto updatedSeat = seatService.updateSeat(id, seatDto);
        return ResponseEntity.ok(updatedSeat);
    }

    // Xóa ghế
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeat(@PathVariable Long id) {
        seatService.deleteSeat(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<Boolean> checkSeat(@PathVariable Long id) {
        Boolean isBooked = seatService.checkSeat(id);
        return ResponseEntity.ok(isBooked);
    }

}
