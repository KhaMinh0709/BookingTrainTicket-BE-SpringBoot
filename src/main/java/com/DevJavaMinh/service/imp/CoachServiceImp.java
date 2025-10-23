package com.DevJavaMinh.service.imp;

import com.DevJavaMinh.dto.CoachDto;
import com.DevJavaMinh.dto.TrainDto;
import com.DevJavaMinh.exception.NotFoundException;
import com.DevJavaMinh.mapper.CoachMapping;
import com.DevJavaMinh.model.Coach;
import com.DevJavaMinh.model.Seat;
import com.DevJavaMinh.model.Train;
import com.DevJavaMinh.repository.CoachRepository;
import com.DevJavaMinh.repository.SeatRepository;
import com.DevJavaMinh.repository.TrainRepository;
import com.DevJavaMinh.service.CoachService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CoachServiceImp implements CoachService {
    private CoachRepository coachRepository;
    private SeatRepository seatRepository;
    private TrainRepository trainRepository;
    private CoachMapping coachMapping;

    @Override
    public List<CoachDto> getAllCoaches() {
        List<Coach> coaches = coachRepository.findAll();

        return coaches.stream().map(coachMapping::toDto).toList();
    }

    @Override
    public CoachDto getCoachById(Long id) {
        Coach coach = coachRepository.findById(id).orElseThrow(()-> new NotFoundException("not found Coach"));
        return coachMapping.toDto(coach);
    }

    @Override
    public CoachDto addCoach(CoachDto coachDto) {
        Coach coachEntity = coachMapping.mapToCoach(coachDto);

        // Lấy đối tượng Train từ trainId
        Train train = trainRepository.findById(coachDto.getTrainId())
                .orElseThrow(() -> new NotFoundException("Train not found"));

        coachEntity.setTrain(train);
        List<Seat> seats = seatRepository.findAllById(coachDto.getSeatList());
        coachEntity.setSeats(seats);
        Coach coachSave = coachRepository.save(coachEntity);
        return coachMapping.toDto(coachSave);
    }


    @Override
    public CoachDto updateCoachById(Long id, CoachDto coachDto) {
        Coach coach = coachRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Coach"));

        coach.setTypeCoach(coachDto.getTypeCoach());

        // Lấy đối tượng Train từ trainId
        Train train = trainRepository.findById(coachDto.getTrainId())
                .orElseThrow(() -> new NotFoundException("Train not found"));
        coach.setTrain(train);

        // Lấy danh sách Seat từ seatRepository dựa trên danh sách seatID trong CoachDto
        List<Seat> seats = seatRepository.findAllById(coachDto.getSeatList());

        if (seats.isEmpty()) {
            throw new NotFoundException("Seats not found");
        }

        // Cập nhật danh sách ghế cho Coach
        coach.setSeats(seats);

        // Lưu lại Coach đã cập nhật vào cơ sở dữ liệu
        Coach updatedCoach = coachRepository.save(coach);

        // Chuyển đổi Coach thành CoachDto để trả về
        return coachMapping.toDto(updatedCoach);
    }


    @Override
    public List<CoachDto> getCoachByTrainId(Long trainid) {
        Train train = trainRepository.findById(trainid)
                .orElseThrow(() -> new NotFoundException("Train not found"));
        List<Coach> listCoachInTrain = coachRepository.findCoachByTrain(train);
        return listCoachInTrain.stream().map(coachMapping::toDto).toList();
    }


}
