package com.qa.cineverse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customers")
@Proxy(lazy=false)
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tickets_id")
    private Long ticketsId;
    @Column(name = "seat_no")
    private Long seatNo;

    @JsonIgnore
    @ManyToMany(targetEntity = Screenings.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "screenings_customers",
            joinColumns=@JoinColumn(name="customers_id"),
            inverseJoinColumns=@JoinColumn(name="screenings_id"))
    private List<Screenings> screenings = new ArrayList<> ();

    @JsonIgnoreProperties("screenings")
    @ManyToMany(targetEntity = Customers.class, fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinTable(
            name = "screenings_customers",
            joinColumns=@JoinColumn(name="screenings_id"),
            inverseJoinColumns=@JoinColumn(name="customers_id"))
    private List<Customers> customers = new ArrayList<>();

    public Tickets() {
    }

    public Tickets(Long seatNo) {
        this.seatNo = seatNo;
    }

    public Tickets(Long ticketsId, Long seatNo) {
        this.ticketsId = getTicketsId();;
        this.seatNo = seatNo;
    }

    public Long getTicketsId() {
        return ticketsId;
    }

    public void setTicketsId(Long ticketsId) {
        this.ticketsId = ticketsId;
    }

    public Long getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Long seatNo) {
        this.seatNo = seatNo;
    }

    public List<Screenings> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<Screenings> screenings) {
        this.screenings = screenings;
    }

    public List<Customers> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customers> customers) {
        this.customers = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Tickets))
            return false;
        Tickets tickets = (Tickets) o;
        return getTicketsId ().equals (tickets.getTicketsId ()) &&
                getSeatNo ().equals (tickets.getSeatNo ()) &&
                getScreenings ().equals (tickets.getScreenings ()) &&
                getCustomers ().equals (tickets.getCustomers ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getTicketsId (), getSeatNo (), getScreenings (), getCustomers ());
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "ticketsId=" + ticketsId +
                ", seatNo=" + seatNo +
                ", screenings=" + screenings +
                ", customers=" + customers +
                '}';
    }
}
