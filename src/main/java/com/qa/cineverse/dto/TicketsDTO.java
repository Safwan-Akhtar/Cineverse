package com.qa.cineverse.dto;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.util.Objects;

@Transactional
public class TicketsDTO {

    private Long ticketsId;
    private Long seatNo;

    public TicketsDTO() {
    }

    public TicketsDTO(Long seatNo) {
        super();
        this.seatNo = seatNo;
    }

    public TicketsDTO(Long ticketsId, Long seatNo) {
        this.ticketsId = ticketsId;
        this.seatNo = seatNo;
    }

    public Long getTicketsId() {
        return ticketsId;
    }

    public void setTicketsId(Long ticketsId) {
        this.ticketsId = ticketsId;
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
        if (!(o instanceof TicketsDTO))
            return false;
        TicketsDTO that = (TicketsDTO) o;
        return getTicketsId ().equals (that.getTicketsId ()) &&
                getSeatNo ().equals (that.getSeatNo ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getTicketsId (), getSeatNo ());
    }

    @Override
    public String toString() {
        return "TicketsDTO{" +
                "ticketsId=" + ticketsId +
                ", seatNo=" + seatNo +
                '}';
    }
}