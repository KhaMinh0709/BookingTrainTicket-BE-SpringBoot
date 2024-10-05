package com.DevJavaMinh.repository;

import com.DevJavaMinh.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}

