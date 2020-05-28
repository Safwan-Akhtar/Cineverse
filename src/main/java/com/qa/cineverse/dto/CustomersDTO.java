package com.qa.cineverse.dto;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Transactional
public class CustomersDTO {
    private Long customersId;
    private String name;
    private List<ScreeningsDTO> orders = new ArrayList<>();

    public CustomersDTO() {
    }

    public CustomersDTO(String name) {
        super();
        this.name = name;
    }

    public CustomersDTO(Long customerId, String name) {
        this.customersId = customerId;
        this.name = name;
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

    public List<ScreeningsDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<ScreeningsDTO> orders) {
        this.orders = orders;
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
                getOrders ().equals (that.getOrders ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getCustomersId (), getName (), getOrders ());
    }

    @Override
    public String toString() {
        return "CustomersDTO{" +
                "customerId=" + customersId +
                ", name='" + name + '\'' +
                ", orders=" + orders +
                '}';
    }
}
