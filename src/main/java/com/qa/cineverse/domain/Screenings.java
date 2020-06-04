package com.qa.cineverse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
    @Table(name = "screenings")
    @Proxy(lazy=false)
    public class Screenings {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "screenings_id")
        private Long screeningsId;
        @Column(name = "movie_date_time")  /// YYYY-MM-DDT00:00:00
        @NonNull
        private LocalDateTime movieDateTime;
        @Column(name = "screen_number")
        @NonNull
        private Long screenNumber;
        @Column(name = "screen_type")
        @NonNull
        private String screenType;
        @Column(name = "movie_name")
        @NonNull
        private String movieName;

        @JsonIgnoreProperties("screenings")
        @ManyToMany(targetEntity = Customers.class, fetch = FetchType.LAZY, cascade= CascadeType.ALL)
        @JoinTable(
                name = "screenings_customers",
                joinColumns=@JoinColumn(name="screenings_id"),
                inverseJoinColumns=@JoinColumn(name="customers_id"))
        private List<Customers> customers = new ArrayList<>();
}
