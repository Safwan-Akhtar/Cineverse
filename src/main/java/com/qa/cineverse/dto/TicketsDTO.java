package com.qa.cineverse.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Transactional
public class TicketsDTO {

    @Getter
    @Setter
    private Long ticketsId;
    @Getter
    @Setter
    private String ticketType;
    @Getter
    @Setter
    private String seatNo;

    public TicketsDTO() {
    }

    public TicketsDTO(String ticketType, String seatNo) {
        this.ticketType = ticketType;
        this.seatNo = seatNo;
    }

    public TicketsDTO(Long ticketsId, String ticketType, String seatNo) {
        this.ticketsId = ticketsId;
        this.ticketType = ticketType;
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