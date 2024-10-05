package com.DevJavaMinh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Coach")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coachID;

    private String typeCoach;

    @ManyToOne
    @JoinColumn(name = "trainID", nullable = false)
    private Train train;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<Seat> seats ;
}

