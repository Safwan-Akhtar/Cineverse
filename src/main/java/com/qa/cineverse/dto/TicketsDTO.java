package com.qa.cineverse.dto;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.util.Objects;

@Transactional
public class TicketsDTO {

    private Long ticketsId;
    private String ticketType;
    private Long seatNo;

    public TicketsDTO() {
    }

    public TicketsDTO(String ticketType, Long seatNo) {
        super();
        this.ticketType = ticketType;
        this.seatNo = seatNo;
    }

    public TicketsDTO(Long ticketsId, String ticketType, Long seatNo) {
        this.ticketsId = ticketsId;
        this.ticketType = ticketType;
        this.seatNo = seatNo;
    }

    public Long getTicketsId() {
        return ticketsId;
    }

    public void setTicketsId(Long ticketsId) {
        this.ticketsId = ticketsId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
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
                getTicketType ().equals (that.getTicketType ()) &&
                getSeatNo ().equals (that.getSeatNo ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getTicketsId (), getTicketType (), getSeatNo ());
    }

    @Override
    public String toString() {
        return "TicketsDTO{" +
                "ticketsId=" + ticketsId +
                ", ticketType='" + ticketType + '\'' +
                ", seatNo=" + seatNo +
                '}';
    }
}