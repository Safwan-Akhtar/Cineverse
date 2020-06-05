package com.qa.cineverse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Transactional
public class TicketsDTO {

    private Long ticketsId;
    @NonNull
    private String ticketType;
    @NonNull
    private String seatNo;
    @NonNull
    private Long screenId;

}