package com.qa.cineverse.repo;

import com.qa.cineverse.domain.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepo extends JpaRepository<Customers, Long> {
}
