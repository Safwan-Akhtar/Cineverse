package com.qa.cineverse.dto;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Transactional
public class ScreeningsDTO {

    private Long screeningsId;
    private LocalDateTime movieDateTime;
    private String screenType;
    private List<CustomersDTO> customers = new ArrayList<> ();

    public ScreeningsDTO() {
    }

    public ScreeningsDTO(LocalDateTime movieDateTime, String screenType) {
        super();
        this.movieDateTime = movieDateTime;
        this.screenType = screenType;
    }

    public ScreeningsDTO(Long screeningsId, LocalDateTime movieDateTime, String screenType) {
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
                getCustomers ().equals (that.getCustomers ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getScreeningsId (), getMovieDateTime (), getScreenType (), getCustomers ());
    }

    @Override
    public String toString() {
        return "ScreeningsDTO{" +
                "screeningsId=" + screeningsId +
                ", movieDateTime='" + movieDateTime + '\'' +
                ", screenType='" + screenType + '\'' +
                ", customers=" + customers +
                '}';
    }
}
