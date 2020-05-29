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
@Table(name = "tickets")
@Proxy(lazy=false)
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tickets_id")
    private Long ticketsId;
    @Column(name = "ticket_type")
    private String ticketType;
    @Column(name = "seat_no")
    private Long seatNo;

    @ManyToOne (targetEntity = Customers.class, fetch = FetchType.LAZY)
    private Customers customers;

    public Tickets() {
    }

    public Tickets(String ticketType, Long seatNo) {
        this.ticketType = ticketType;
        this.seatNo = seatNo;
    }

    public Tickets(Long ticketsId, String ticketType, Long seatNo) {
        this.ticketsId = getTicketsId();
        this.ticketType = ticketType;
        this.seatNo = seatNo;
    }

    public Long getTicketsId() {
        return ticketsId;
    }

    public void setTicketsId(Long ticketsId) {
        this.ticketsId = ticketsId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Long getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Long seatNo) {
        this.seatNo = seatNo;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
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
                getTicketType ().equals (tickets.getTicketType ()) &&
                getSeatNo ().equals (tickets.getSeatNo ()) &&
                getCustomers ().equals (tickets.getCustomers ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getTicketsId (), getTicketType (), getSeatNo (), getCustomers ());
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "ticketsId=" + ticketsId +
                ", ticketType=" + ticketType +
                ", seatNo=" + seatNo +
                ", customers=" + customers +
                '}';
    }
}