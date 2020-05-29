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
    private Long seatNo;


    public CustomersDTO() {
    }

    public CustomersDTO(String name, Long seatNo) {
        super();
        this.name = name;
        this.seatNo = seatNo;
    }

    public CustomersDTO(Long customerId, String name, Long seatNo) {
        this.customersId = customerId;
        this.name = name;
        this.seatNo = seatNo;
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

    public Long getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Long seatNo) {
        this.seatNo = seatNo;
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
                getSeatNo ().equals (that.getSeatNo ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getCustomersId (), getName (), getSeatNo ());
    }

    @Override
    public String toString() {
        return "CustomersDTO{" +
                "customersId=" + customersId +
                ", name='" + name + '\'' +
                ", seatNo=" + seatNo +
                '}';
    }
}
