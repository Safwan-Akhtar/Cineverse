package com.qa.cineverse.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "customers")
@Proxy(lazy=false)
public class Customers {

    @Id
    @GeneratedValue
    @Column(name = "customers_id")
    private Long customersId;

    @Column(name = "name")
    @NonNull
    private String name;
    @Column(name = "username")
    @NonNull
    private String username;

    @JsonIgnore
    @ManyToMany(targetEntity = Screenings.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "screenings_customers",
            joinColumns=@JoinColumn(name="customers_id"),
            inverseJoinColumns=@JoinColumn(name="screenings_id"))
    private List<Screenings> screenings = new ArrayList<> ();

    @JsonIgnore
    @ManyToMany(targetEntity = Tickets.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "tickets_customers",
            joinColumns=@JoinColumn(name="customers_id"),
            inverseJoinColumns=@JoinColumn(name="tickets_id"))
    private List<Tickets> tickets = new ArrayList<> ();
}
