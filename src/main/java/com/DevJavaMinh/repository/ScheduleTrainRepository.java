package com.DevJavaMinh.repository;

import com.DevJavaMinh.model.ScheduleTrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleTrainRepository extends JpaRepository<ScheduleTrain, Long> {

    @Query("SELECT st FROM ScheduleTrain st " +
            "WHERE st.schedule.departureStation = :departureStation " +
            "AND st.schedule.arrivalStation = :arrivalStation " +
            "AND DATE(st.departureTime) = DATE(:departureDate)")
    List<ScheduleTrain> findTrainsByDateAndStations(
            @Param("departureStation") String departureStation,
            @Param("arrivalStation") String arrivalStation,
            @Param("departureDate") Date departureDate
    );

    @Query("SELECT st FROM ScheduleTrain st " +
            "WHERE st.schedule.departureStation = :departureStation " +
            "AND st.schedule.arrivalStation = :arrivalStation")
    List<ScheduleTrain> findTrainsByStations(
            @Param("departureStation") String departureStation,
            @Param("arrivalStation") String arrivalStation
    );

}
