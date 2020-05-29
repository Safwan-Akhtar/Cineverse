package com.qa.cineverse.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Transactional
public class CustomersDTO {
    private Long customersId;
    private String name;
    private List<TicketsDTO> tickets = new ArrayList<>();


    public CustomersDTO() {
    }

    public CustomersDTO(String name) {
        super();
        this.name = name;
    }

    public CustomersDTO(Long customerId, String name, List<TicketsDTO> tickets) {
        this.customersId = customerId;
        this.name = name;
        this.tickets = tickets;
    }

    public Long getCustomersId() {
        return customersId;
    }

    public void setCustomersId(Long customerId) {
        this.customersId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TicketsDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketsDTO> tickets) {
        this.tickets = tickets;
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
