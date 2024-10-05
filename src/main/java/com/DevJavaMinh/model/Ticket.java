package com.DevJavaMinh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketid;

    @ManyToOne
    @JoinColumn(name = "scheduleid")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "seatid")
    private Seat seat;
}
