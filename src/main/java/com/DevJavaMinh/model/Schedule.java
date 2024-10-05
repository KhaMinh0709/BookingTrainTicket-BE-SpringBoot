package com.DevJavaMinh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Schedule")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduleID")
    private Long scheduleID;

    @ManyToMany
    @JoinTable(name = "schedule_train",
            joinColumns = @JoinColumn(name = "scheduleID"),
            inverseJoinColumns = @JoinColumn(name = "trainID"))
    private List<Train> trains;

    private Date departureTime;
    private Date arrivalTime;
    private String departureStation;
    private String arrivalStation;
    private double price;
}

