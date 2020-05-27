package com.qa.cineverse.service;

import com.qa.cineverse.domain.Screenings;
import com.qa.cineverse.dto.ScreeningsDTO;
import com.qa.cineverse.exception.ScreeningsNotFoundException;
import com.qa.cineverse.repo.ScreeningsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScreeningsService {

    private final ScreeningsRepo repo;

    private final ModelMapper mapper;

    @Autowired
    public ScreeningsService(ScreeningsRepo repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private ScreeningsDTO mapToDTO(Screenings screenings) { return this.mapper.map(screenings, ScreeningsDTO.class); }

    public List<ScreeningsDTO> readScreenings() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ScreeningsDTO createScreening(Screenings screenings) {
        Screenings tempScreenings = this.repo.save(screenings);
        return this.mapToDTO (tempScreenings);
    }

    public ScreeningsDTO findScreeningsById(Long id) {
        return this.mapToDTO(this.repo.findById(id)
                .orElseThrow(ScreeningsNotFoundException::new));
    }

    public boolean deleteScreening(Long id) {
        if(!this.repo.existsById(id)) {
            throw new ScreeningsNotFoundException ();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }



}
