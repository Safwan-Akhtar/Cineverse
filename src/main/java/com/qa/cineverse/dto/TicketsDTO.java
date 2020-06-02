package com.qa.cineverse.dto;

import lombok.*;
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
}