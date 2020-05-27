package com.qa.cineverse.dto;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

@Transactional
public class OrdersDTO {

    private Long ordersId;
    private String movieDateTime;
    private Long imdbId;
    private BigDecimal totalPrice;
    private Long seatNo;

    public OrdersDTO() {
    }

    public OrdersDTO(String movieDateTime, Long imdbId, BigDecimal totalPrice, Long seatNo) {
        super();
        this.movieDateTime = movieDateTime;
        this.imdbId = imdbId;
        this.totalPrice = totalPrice;
        this.seatNo = seatNo;
    }

    public OrdersDTO(Long ordersId, String movieDateTime, Long imdbId, BigDecimal totalPrice, Long seatNo) {
        this.ordersId = ordersId;
        this.movieDateTime = movieDateTime;
        this.imdbId = imdbId;
        this.totalPrice = totalPrice;
        this.seatNo = seatNo;
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

    public Long getImdbId() {
        return imdbId;
    }

    public void setImdbId(Long imdbId) {
        this.imdbId = imdbId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Long seatNo) {
        this.seatNo = seatNo;
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
                getImdbId ().equals (ordersDTO.getImdbId ()) &&
                getTotalPrice ().equals (ordersDTO.getTotalPrice ()) &&
                getSeatNo ().equals (ordersDTO.getSeatNo ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getOrdersId (), getMovieDateTime (), getImdbId (), getTotalPrice (), getSeatNo ());
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "ordersId=" + ordersId +
                ", movieDateTime='" + movieDateTime + '\'' +
                ", imdbId=" + imdbId +
                ", totalPrice=" + totalPrice +
                ", seatNo=" + seatNo +
                '}';
    }
}
