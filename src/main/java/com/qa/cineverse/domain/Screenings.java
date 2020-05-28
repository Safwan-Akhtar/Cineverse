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
        @GeneratedValue
        @Column(name = "screenings_id")
        private Long screeningsId;
        @Column(name = "movie_date_time")  /// YYYY-MM-DDT00:00:00
        private LocalDateTime movieDateTime;
        @Column(name = "screen_number")
        private Long screenNumber;
        @Column(name = "screen_type")
        private String screenType;
        @Column(name = "movie_name")
        private String movieName;

        @ManyToMany(targetEntity = Customers.class, fetch = FetchType.LAZY, cascade= CascadeType.ALL)
        @JoinTable(
                name = "screenings_customers",
                joinColumns=@JoinColumn(name="screenings_id"),
                inverseJoinColumns=@JoinColumn(name="customers_id"))
        private List<Customers> customers = new ArrayList<>();

        public Screenings() {
        }

    public Screenings(LocalDateTime movieDateTime, Long screenNumber, String screenType, String movieName) {
        this.movieDateTime = movieDateTime;
        this.screenNumber = screenNumber;
        this.screenType = screenType;
        this.movieName = movieName;
    }

    public Screenings(Long screeningsId, LocalDateTime movieDateTime, Long screenNumber, String screenType, String movieName) {
        this.screeningsId = screeningsId;
        this.movieDateTime = movieDateTime;
        this.screenNumber = screenNumber;
        this.screenType = screenType;
        this.movieName = movieName;
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

    public Long getScreenNumber() {
        return screenNumber;
    }

    public void setScreenNumber(Long screenNumber) {
        this.screenNumber = screenNumber;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
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
        if (!(o instanceof Screenings))
            return false;
        Screenings that = (Screenings) o;
        return getScreenNumber () == that.getScreenNumber () &&
                getScreeningsId ().equals (that.getScreeningsId ()) &&
                getMovieDateTime ().equals (that.getMovieDateTime ()) &&
                getScreenType ().equals (that.getScreenType ()) &&
                getMovieName ().equals (that.getMovieName ()) &&
                getCustomers ().equals (that.getCustomers ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getScreeningsId (), getMovieDateTime (), getScreenNumber (), getScreenType (), getMovieName (), getCustomers ());
    }
}
