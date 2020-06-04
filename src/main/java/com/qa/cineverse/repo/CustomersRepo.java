package com.qa.cineverse.repo;

import com.qa.cineverse.domain.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomersRepo extends JpaRepository<Customers, Long> {
    List<Customers> findByName(String name);
}
