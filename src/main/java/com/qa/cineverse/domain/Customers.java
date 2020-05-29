package com.qa.cineverse.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "customers")
@Proxy(lazy=false)
public class Customers {

    @Id
    @GeneratedValue
    @Column(name = "customers_id")
    @Getter
    @Setter
    private Long customersId;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @JsonIgnore
    @ManyToMany(targetEntity = Screenings.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "screenings_customers",
            joinColumns=@JoinColumn(name="customers_id"),
            inverseJoinColumns=@JoinColumn(name="screenings_id"))
    @Getter
    @Setter
    private List<Screenings> screenings = new ArrayList<> ();

    @JsonIgnore
    @ManyToMany(targetEntity = Tickets.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "tickets_customers",
            joinColumns=@JoinColumn(name="customers_id"),
            inverseJoinColumns=@JoinColumn(name="tickets_id"))
    @Getter
    @Setter
    private List<Tickets> tickets = new ArrayList<> ();

    public Customers(String name) {
        this.name = name;
    }

    public Customers(Long customersId, String name) {
        this.customersId = customersId;
        this.name = name;
    }
}
