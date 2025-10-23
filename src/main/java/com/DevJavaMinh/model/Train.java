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
    @Column(name = "trainID")
    private Long trainID;

    private String trainName;

    @Column(name = "capacityTrain", nullable = false)
    private int capacityTrain;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Coach> coaches;

}
