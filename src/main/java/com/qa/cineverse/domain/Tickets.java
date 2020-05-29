package com.qa.cineverse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
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
    @Getter
    @Setter
    private Long ticketsId;
    @Column(name = "ticket_type")
    @Getter
    @Setter
    private String ticketType;
    @Column(name = "seat_no")
    @Getter
    @Setter
    private String seatNo;

    @JsonIgnoreProperties("tickets")
    @ManyToMany(targetEntity = Customers.class, fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinTable(
            name = "tickets_customers",
            joinColumns=@JoinColumn(name="tickets_id"),
            inverseJoinColumns=@JoinColumn(name="customers_id"))
    @Getter
    @Setter
    private List<Customers> customers = new ArrayList<>();

    public Tickets() {
    }

    public Tickets(String ticketType, String seatNo) {
        super();
        this.ticketType = ticketType;
        this.seatNo = seatNo;
    }

    public Tickets(Long ticketsId, String ticketType, String seatNo) {
        this.ticketsId = getTicketsId();
        this.ticketType = ticketType;
        this.seatNo = seatNo;
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
                ", ticketType='" + ticketType + '\'' +
                ", seatNo='" + seatNo + '\'' +
                ", customers=" + customers +
                '}';
    }
}