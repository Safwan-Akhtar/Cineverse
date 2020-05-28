package com.qa.cineverse.domain;


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

    @ManyToMany(targetEntity = Screenings.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "screenings_customers",
            joinColumns=@JoinColumn(name="customers_id"),
            inverseJoinColumns=@JoinColumn(name="screenings_id"))
    private List<Screenings> orders = new ArrayList<> ();

    public Customers() {
    }

    public Customers(String name) {
        this.name = name;
    }

    public Customers(Long customersId, String name) {
        this.customersId = customersId;
        this.name = name;
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

    public List<Screenings> getOrders() {
        return orders;
    }

    public void setOrders(List<Screenings> orders) {
        this.orders = orders;
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
                getOrders ().equals (customers.getOrders ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getCustomersId (), getName (), getOrders ());
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customersId=" + customersId +
                ", name='" + name + '\'' +
                ", orders=" + orders +
                '}';
    }
}
