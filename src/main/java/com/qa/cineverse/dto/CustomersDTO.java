package com.qa.cineverse.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Transactional
public class CustomersDTO {

    @Getter
    @Setter
    private Long customersId;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private List<TicketsDTO> tickets = new ArrayList<>();


    public CustomersDTO() {
    }

    public CustomersDTO(String name) {
        this.name = name;
    }

    public CustomersDTO(Long customerId, String name) {
        this.customersId = customerId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CustomersDTO))
            return false;
        CustomersDTO that = (CustomersDTO) o;
        return getCustomersId ().equals (that.getCustomersId ()) &&
                getName ().equals (that.getName ()) &&
                getTickets ().equals (that.getTickets ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getCustomersId (), getName (), getTickets ());
    }

    @Override
    public String toString() {
        return "CustomersDTO{" +
                "customersId=" + customersId +
                ", name='" + name + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}
