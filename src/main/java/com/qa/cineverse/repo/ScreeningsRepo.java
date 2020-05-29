package com.qa.cineverse.repo;

import com.qa.cineverse.domain.Screenings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreeningsRepo extends JpaRepository<Screenings, Long> {
    List<Screenings> findByMovieName(String movieName);
}
