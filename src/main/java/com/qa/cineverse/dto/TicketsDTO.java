package com.qa.cineverse.dto;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;

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