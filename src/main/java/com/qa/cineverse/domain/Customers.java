package com.qa.cineverse.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customers")
@Proxy(lazy=false)
public class Customers {

    @Id
    @GeneratedValue
    @Column(name = "customers_id")
    private Long customersId;

    @Column(name = "name")
    private String name;
    @Column(name = "seat_no")
    private Long seatNo;

    @JsonIgnore
    @ManyToMany(targetEntity = Screenings.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "screenings_customers",
            joinColumns=@JoinColumn(name="customers_id"),
            inverseJoinColumns=@JoinColumn(name="screenings_id"))
    private List<Screenings> screenings = new ArrayList<> ();

    public Customers() {
    }

    public Customers(String name, Long seatNo) {
        this.name = name;
        this.seatNo = seatNo;
    }

    public Customers(Long customersId, String name, Long seatNo) {
        this.customersId = customersId;
        this.name = name;
        this.seatNo = seatNo;
    }

    public Long getCustomersId() {
        return customersId;
    }

    public void setCustomersId(Long customerId) {
        this.customersId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Customers))
            return false;
        Customers customers = (Customers) o;
        return getCustomersId ().equals (customers.getCustomersId ()) &&
                getName ().equals (customers.getName ()) &&
                getSeatNo ().equals (customers.getSeatNo ()) &&
                getScreenings ().equals (customers.getScreenings ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getCustomersId (), getName (), getSeatNo (), getScreenings ());
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customersId=" + customersId +
                ", name='" + name + '\'' +
                ", seatNo='" + seatNo + '\'' +
                ", screenings=" + screenings +
                '}';
    }
}
