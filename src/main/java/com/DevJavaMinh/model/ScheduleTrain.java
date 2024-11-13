package com.DevJavaMinh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "schedule_train")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleTrain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "scheduleID", nullable = false)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "trainID", nullable = false)
    private Train train;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;

    private double price;
}
