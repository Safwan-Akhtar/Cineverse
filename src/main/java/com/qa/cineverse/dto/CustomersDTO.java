package com.qa.cineverse.dto;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Transactional
public class CustomersDTO {


    private Long customersId;
    @NonNull
    private String name;
    @NonNull
    private List<TicketsDTO> tickets = new ArrayList<>();

}
