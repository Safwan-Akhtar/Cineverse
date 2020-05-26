package com.qa.cineverse.domain;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customers")
@Proxy(lazy=false)
public class Customers {

    @Id
    @GeneratedValue
    private Long customerId;

    @Column(name = "name")
    private String name;

    public Customers() {
    }

    public Customers(String name) {
        this.name = name;
    }

    public Customers(Long customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Customers))
            return false;
        Customers customers = (Customers) o;
        return getCustomerId ().equals (customers.getCustomerId ()) &&
                getName ().equals (customers.getName ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getCustomerId (), getName ());
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                '}';
    }
}
