package com.qa.cineverse.domain;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Proxy(lazy=false)
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tickets_id")
    private Long ticketsId;
    @Column(name = "seatNo")
    private LocalDateTime movieDateTime;
    @Column(name = "screen_number")
    private Long screenNumber;
    @Column(name = "screen_type")
    private String screenType;
    @Column(name = "movie_name")
    private String movieName;

}
