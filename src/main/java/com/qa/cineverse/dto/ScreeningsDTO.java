package com.qa.cineverse.dto;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Transactional
public class ScreeningsDTO {

    private Long screeningsId;
    private LocalDateTime movieDateTime;
    private String screenType;
    private Long screenNumber;
    private String movieName;
    private List<CustomersDTO> customers = new ArrayList<> ();

    public ScreeningsDTO() {
    }

    public ScreeningsDTO(LocalDateTime movieDateTime, Long screenNumber, String screenType, String movieName) {
        super();
        this.movieDateTime = movieDateTime;
        this.screenNumber = screenNumber;
        this.screenType = screenType;
        this.movieName = movieName;
    }

    public ScreeningsDTO(Long screeningsId, LocalDateTime movieDateTime, Long screenNumber, String screenType, String movieName) {
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

    public List<CustomersDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomersDTO> customers) {
        this.customers = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ScreeningsDTO))
            return false;
        ScreeningsDTO that = (ScreeningsDTO) o;
        return getScreeningsId ().equals (that.getScreeningsId ()) &&
                getMovieDateTime ().equals (that.getMovieDateTime ()) &&
                getScreenType ().equals (that.getScreenType ()) &&
                getScreenNumber ().equals (that.getScreenNumber ()) &&
                getMovieName ().equals (that.getMovieName ()) &&
                getCustomers ().equals (that.getCustomers ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getScreeningsId (), getMovieDateTime (), getScreenType (), getScreenNumber (), getMovieName (), getCustomers ());
    }

    @Override
    public String toString() {
        return "ScreeningsDTO{" +
                "screeningsId=" + screeningsId +
                ", movieDateTime=" + movieDateTime +
                ", screenType='" + screenType + '\'' +
                ", screenNumber=" + screenNumber +
                ", movieName='" + movieName + '\'' +
                ", customers=" + customers +
                '}';
    }
}
