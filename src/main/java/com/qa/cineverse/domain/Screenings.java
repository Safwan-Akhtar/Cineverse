package com.qa.cineverse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
    @Table(name = "screenings")
    @Proxy(lazy=false)
    public class Screenings {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "screenings_id")
        @Getter @Setter @NonNull
        private Long screeningsId;
        @Column(name = "movie_date_time")  /// YYYY-MM-DDT00:00:00
        @Getter @Setter @NonNull
        private LocalDateTime movieDateTime;
        @Column(name = "screen_number")
        @Getter @Setter @NonNull
        private Long screenNumber;
        @Column(name = "screen_type")
        @Getter @Setter @NonNull
        private String screenType;
        @Column(name = "movie_name")
        @Getter @Setter @NonNull
        private String movieName;

        @JsonIgnoreProperties("screenings")
        @ManyToMany(targetEntity = Customers.class, fetch = FetchType.LAZY, cascade= CascadeType.ALL)
        @JoinTable(
                name = "screenings_customers",
                joinColumns=@JoinColumn(name="screenings_id"),
                inverseJoinColumns=@JoinColumn(name="customers_id"))
        @Getter @Setter
        private List<Customers> customers = new ArrayList<>();

    public Screenings(LocalDateTime movieDateTime, Long screenNumber, String screenType, String movieName) {
        this.movieDateTime = movieDateTime;
        this.screenNumber = screenNumber;
        this.screenType = screenType;
        this.movieName = movieName;
    }
}
