package com.qa.cineverse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
    @Table(name = "orders")
    @Proxy(lazy=false)
    public class Orders {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "orders_id")
        private Long ordersId;
        @Column(name = "movie_date_time")  /// YYYY-MM-DDT00:00:00
        private LocalDateTime movieDateTime;
        @Column(name = "screen_type")
        private String screenType;

        @ManyToMany(targetEntity = Customers.class, fetch = FetchType.LAZY, cascade= CascadeType.ALL)
        @JoinTable(
                name = "orders_customers",
                joinColumns=@JoinColumn(name="orders_id"),
                inverseJoinColumns=@JoinColumn(name="customers_id"))
        private Set<Customers> customers = new HashSet<>();

        public Orders() {
        }

    public Orders(LocalDateTime movieDateTime, String imdbId, String totalPrice, String screenType, Set<Customers> customers) {
        this.movieDateTime = movieDateTime;
        this.screenType = screenType;
        this.customers = customers;
    }

    public Orders(Long ordersId, LocalDateTime movieDateTime, String imdbId, String totalPrice, String screenType, Set<Customers> customers) {
        this.ordersId = ordersId;
        this.movieDateTime = movieDateTime;
        this.screenType = screenType;
        this.customers = customers;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long orderId) {
        this.ordersId = orderId;
    }

    public LocalDateTime getMovieDateTime() {
        return movieDateTime;
    }

    public void setMovieDateTime(LocalDateTime movieDateTime) {
        this.movieDateTime = movieDateTime;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String seatNo) {
        this.screenType = seatNo;
    }

    public Set<Customers> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customers> customers) {
        this.customers = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Orders))
            return false;
        Orders orders = (Orders) o;
        return getOrdersId ().equals (orders.getOrdersId ()) &&
                getMovieDateTime ().equals (orders.getMovieDateTime ()) &&
                getScreenType ().equals (orders.getScreenType ()) &&
                getCustomers ().equals (orders.getCustomers ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getOrdersId (), getMovieDateTime (), getScreenType (), getCustomers ());
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + ordersId +
                ", movieDateTime='" + movieDateTime + '\'' +
                ", seatNo='" + screenType + '\'' +
                ", customers=" + customers +
                '}';
    }
}
