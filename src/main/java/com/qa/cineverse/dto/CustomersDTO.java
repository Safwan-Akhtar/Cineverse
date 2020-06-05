package com.qa.cineverse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
    private String username;
    @NonNull
    private List<TicketsDTO> tickets = new ArrayList<>();

}
