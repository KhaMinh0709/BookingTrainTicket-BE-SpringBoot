package com.DevJavaMinh.repository;

import com.DevJavaMinh.model.Coach;
import com.DevJavaMinh.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    // Tìm danh sách ghế dựa trên đối tượng Coach
    List<Seat> findByCoach(Coach coach);

    // Tìm ghế theo số ghế
    Seat findBySeatNumber(int seatNumber);
}
