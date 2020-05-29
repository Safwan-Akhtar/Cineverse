package com.qa.cineverse.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Transactional
public class ScreeningsDTO {

    @Getter
    @Setter
    private Long screeningsId;
    @Getter
    @Setter
    private LocalDateTime movieDateTime;
    @Getter
    @Setter
    private String screenType;
    @Getter
    @Setter
    private Long screenNumber;
    @Getter
    @Setter
    private String movieName;
    @Getter
    @Setter
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
