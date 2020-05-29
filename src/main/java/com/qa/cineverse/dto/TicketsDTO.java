package com.qa.cineverse.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@EqualsAndHashCode
@ToString
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
}