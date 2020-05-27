package com.qa.cineverse.dto;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

@Transactional
public class OrdersDTO {

    private Long ordersId;
    private String movieDateTime;
    private String screenType;

    public OrdersDTO() {
    }

    public OrdersDTO(String movieDateTime, String screenType) {
        this.movieDateTime = movieDateTime;
        this.screenType = screenType;
    }

    public OrdersDTO(Long ordersId, String movieDateTime, String screenType) {
        this.ordersId = ordersId;
        this.movieDateTime = movieDateTime;
        this.screenType = screenType;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
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
        if (!(o instanceof OrdersDTO))
            return false;
        OrdersDTO ordersDTO = (OrdersDTO) o;
        return getOrdersId ().equals (ordersDTO.getOrdersId ()) &&
                getMovieDateTime ().equals (ordersDTO.getMovieDateTime ()) &&
                getScreenType ().equals (ordersDTO.getScreenType ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getOrdersId (), getMovieDateTime (), getScreenType ());
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "ordersId=" + ordersId +
                ", movieDateTime='" + movieDateTime + '\'' +
                ", screenType='" + screenType + '\'' +
                '}';
    }
}
