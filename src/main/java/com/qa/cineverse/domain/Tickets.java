package com.qa.cineverse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tickets")
@Proxy(lazy=false)
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tickets_id")
    private Long ticketsId;
    @Column(name = "ticket_type")
    @NonNull
    private String ticketType;
    @Column(name = "seat_no")
    @NonNull
    private String seatNo;

    @JsonIgnoreProperties("tickets")
    @ManyToMany(targetEntity = Customers.class, fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinTable(
            name = "tickets_customers",
            joinColumns=@JoinColumn(name="tickets_id"),
            inverseJoinColumns=@JoinColumn(name="customers_id"))
    private List<Customers> customers = new ArrayList<>();
}