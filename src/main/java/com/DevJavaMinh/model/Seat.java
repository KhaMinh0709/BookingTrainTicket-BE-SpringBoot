package com.DevJavaMinh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Seat")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatID;

    @ManyToOne
    @JoinColumn(name = "coachID", nullable = false)
    private Coach coach; // Toa mà ghế thuộc về

    @Column(name = "seatNumber", nullable = false)
    private int seatNumber; // Số ghế

    private Boolean isBooking;

}

