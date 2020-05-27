package com.qa.cineverse.dto;

import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Transactional
public class CustomersDTO {
    private Long customerId;
    private String name;

    public CustomersDTO() {
    }

    public CustomersDTO(String name) {
        super();
        this.name = name;
    }

    public CustomersDTO(Long customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CustomersDTO))
            return false;
        CustomersDTO that = (CustomersDTO) o;
        return getCustomerId ().equals (that.getCustomerId ()) &&
                getName ().equals (that.getName ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getCustomerId (), getName ());
    }

    @Override
    public String toString() {
        return "CustomersDTO{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                '}';
    }
}
