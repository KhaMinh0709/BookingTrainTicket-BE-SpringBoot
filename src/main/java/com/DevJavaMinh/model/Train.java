package com.DevJavaMinh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Train")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainID;

    @Column(name = "trainName", nullable = false, unique = true)
    private String trainName;

    @Column(name = "capacityTrain", nullable = false)
    private int capacityTrain;
    
}
