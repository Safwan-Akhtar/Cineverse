package com.qa.cineverse.repo;

import com.qa.cineverse.domain.Screenings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketsRepo extends JpaRepository<Screenings, Long> {
}