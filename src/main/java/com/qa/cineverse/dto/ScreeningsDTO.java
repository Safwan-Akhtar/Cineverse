package com.qa.cineverse.dto;

import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Transactional
public class ScreeningsDTO {

    private Long screeningsId;
    private String movieDateTime;
    private String screenType;

    public ScreeningsDTO() {
    }

    public ScreeningsDTO(String movieDateTime, String screenType) {
        this.movieDateTime = movieDateTime;
        this.screenType = screenType;
    }

    public ScreeningsDTO(Long screeningsId, String movieDateTime, String screenType) {
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

    public String getMovieDateTime() {
        return movieDateTime;
    }

    public void setMovieDateTime(String movieDateTime) {
        this.movieDateTime = movieDateTime;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ScreeningsDTO))
            return false;
        ScreeningsDTO screeningsDTO = (ScreeningsDTO) o;
        return getScreeningsId ().equals (screeningsDTO.getScreeningsId ()) &&
                getMovieDateTime ().equals (screeningsDTO.getMovieDateTime ()) &&
                getScreenType ().equals (screeningsDTO.getScreenType ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getScreeningsId (), getMovieDateTime (), getScreenType ());
    }

    @Override
    public String toString() {
        return "ScreeningsDTO{" +
                "screeningsId=" + screeningsId +
                ", movieDateTime='" + movieDateTime + '\'' +
                ", screenType='" + screenType + '\'' +
                '}';
    }
}
