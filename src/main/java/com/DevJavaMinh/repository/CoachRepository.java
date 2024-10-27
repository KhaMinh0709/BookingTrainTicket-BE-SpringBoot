package com.DevJavaMinh.repository;

import com.DevJavaMinh.model.Coach;
import com.DevJavaMinh.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
    //
    List<Coach> findCoachByTrain(Train train);

}
