package com.DevJavaMinh.repository;

import com.DevJavaMinh.dto.TrainDto;
import com.DevJavaMinh.model.Schedule;
import com.DevJavaMinh.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT st.train FROM Schedule s " +
            "JOIN s.scheduleTrains st " +
            "WHERE s.departureStation = :departureStation " +
            "AND s.arrivalStation = :arrivalStation " +
            "AND FUNCTION('DATE', st.departureTime) = :departureDate")
    List<Train> findTrainsByScheduleAndDepartureDate(
            @Param("departureStation") String departureStation,
            @Param("arrivalStation") String arrivalStation,
            @Param("departureDate") Date departureDate);


}

