package com.qa.cineverse.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Transactional
public class CustomersDTO {

    @Getter @Setter
    private Long customersId;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private List<TicketsDTO> tickets = new ArrayList<>();

    public CustomersDTO(String name) {
        this.name = name;
    }

    public CustomersDTO(Long customerId, String name) {
        this.customersId = customerId;
        this.name = name;
    }
}
