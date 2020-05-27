package com.qa.cineverse.domain;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
    @Table(name = "screenings")
    @Proxy(lazy=false)
    public class Screenings {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "screenings_id")
        private Long screeningsId;
        @Column(name = "movie_date_time")  /// YYYY-MM-DDT00:00:00
        private LocalDateTime movieDateTime;
        @Column(name = "screen_type")
        private String screenType;

        @ManyToMany(targetEntity = Customers.class, fetch = FetchType.LAZY, cascade= CascadeType.ALL)
        @JoinTable(
                name = "screenings_customers",
                joinColumns=@JoinColumn(name="screenings_id"),
                inverseJoinColumns=@JoinColumn(name="customers_id"))
        private Set<Customers> customers = new HashSet<>();

        public Screenings() {
        }

    public Screenings(LocalDateTime movieDateTime, String screenType) {
        this.movieDateTime = movieDateTime;
        this.screenType = screenType;
    }

    public Screenings(Long screeningsId, LocalDateTime movieDateTime, String screenType) {
        this.screeningsId = screeningsId;
        this.movieDateTime = movieDateTime;
        this.screenType = screenType;
    }

    public Long getScreeningsId() {
        return screeningsId;
    }

    public void setScreeningsId(Long screeningsId) {
        this.screeningsId = screeningsId;
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

    public void setScreenType(String screenType) {
        this.screenType = screenType;
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
        if (!(o instanceof Screenings))
            return false;
        Screenings that = (Screenings) o;
        return getScreeningsId ().equals (that.getScreeningsId ()) &&
                getMovieDateTime ().equals (that.getMovieDateTime ()) &&
                getScreenType ().equals (that.getScreenType ()) &&
                getCustomers ().equals (that.getCustomers ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getScreeningsId (), getMovieDateTime (), getScreenType (), getCustomers ());
    }

    @Override
    public String toString() {
        return "Screenings{" +
                "screeningsId=" + screeningsId +
                ", movieDateTime=" + movieDateTime +
                ", screenType='" + screenType + '\'' +
                '}';
    }
}
