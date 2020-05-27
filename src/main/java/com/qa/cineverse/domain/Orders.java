package com.qa.cineverse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
    @Table(name = "orders")
    @Proxy(lazy=false)
    public class Orders {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long orderId;
        @Column(name = "movie_date_time")
        private String movieDateTime;
        @Column(name = "imdb_id")
        private String imdbId;
        @Column(name = "total_price")
        private String totalPrice;
        @Column(name = "seat_no")
        private String seatNo;

        @JsonIgnore
        @ManyToMany(cascade= CascadeType.ALL)
        private Set<Customers> customers = new HashSet<> ();

        public Orders() {
        }

    public Orders(String movieDateTime, String imdbId, String totalPrice, String seatNo, Set<Customers> customers) {
        this.movieDateTime = movieDateTime;
        this.imdbId = imdbId;
        this.totalPrice = totalPrice;
        this.seatNo = seatNo;
        this.customers = customers;
    }

    public Orders(Long orderId, String movieDateTime, String imdbId, String totalPrice, String seatNo, Set<Customers> customers) {
        this.orderId = orderId;
        this.movieDateTime = movieDateTime;
        this.imdbId = imdbId;
        this.totalPrice = totalPrice;
        this.seatNo = seatNo;
        this.customers = customers;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getMovieDateTime() {
        return movieDateTime;
    }

    public void setMovieDateTime(String movieDateTime) {
        this.movieDateTime = movieDateTime;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
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
        return getOrderId ().equals (orders.getOrderId ()) &&
                getMovieDateTime ().equals (orders.getMovieDateTime ()) &&
                getImdbId ().equals (orders.getImdbId ()) &&
                getTotalPrice ().equals (orders.getTotalPrice ()) &&
                getSeatNo ().equals (orders.getSeatNo ()) &&
                getCustomers ().equals (orders.getCustomers ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getOrderId (), getMovieDateTime (), getImdbId (), getTotalPrice (), getSeatNo (), getCustomers ());
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", movieDateTime='" + movieDateTime + '\'' +
                ", imdbId='" + imdbId + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", seatNo='" + seatNo + '\'' +
                ", customers=" + customers +
                '}';
    }
}
