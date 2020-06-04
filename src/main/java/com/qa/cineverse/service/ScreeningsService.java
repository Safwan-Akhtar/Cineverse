package com.qa.cineverse.service;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.domain.Screenings;
import com.qa.cineverse.dto.ScreeningsDTO;
import com.qa.cineverse.exception.ScreeningsNotFoundException;
import com.qa.cineverse.repo.CustomersRepo;
import com.qa.cineverse.repo.ScreeningsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScreeningsService {

    private final ScreeningsRepo screeningsRepo;

    private final CustomersRepo customersRepo;

    private final ModelMapper mapper;

    @Autowired
    public ScreeningsService(ScreeningsRepo screeningsRepo, CustomersRepo customersRepo, ModelMapper mapper) {
        this.screeningsRepo = screeningsRepo;
        this.customersRepo = customersRepo;
        this.mapper = mapper;
    }

    private ScreeningsDTO mapToDTO(Screenings screenings) { return this.mapper.map(screenings, ScreeningsDTO.class); }

    public List<ScreeningsDTO> readScreenings() {
        return this.screeningsRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ScreeningsDTO createScreening(Screenings screenings) {
        Screenings tempScreenings = this.screeningsRepo.save(screenings);
        return this.mapToDTO (tempScreenings);
    }

    public ScreeningsDTO findScreeningsById(Long id) {
        return this.mapToDTO(this.screeningsRepo.findById(id)
                .orElseThrow(ScreeningsNotFoundException::new));
    }

    public List<ScreeningsDTO> readScreeningsByName(String name) {
        return this.screeningsRepo.findByMovieName(name).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public boolean deleteScreening(Long id) {
        if(!this.screeningsRepo.existsById(id)) {
            throw new ScreeningsNotFoundException ();
        }
        this.screeningsRepo.deleteById(id);
        return this.screeningsRepo.existsById(id);
    }

    public ScreeningsDTO addCustomerToScreening(Long id, Customers customers){
        Screenings screenings = this.screeningsRepo.findById(id).orElseThrow(ScreeningsNotFoundException::new);
        Customers tmp = this.customersRepo.saveAndFlush(customers);
        screenings.getCustomers ().add(tmp);
        return this.mapToDTO(this.screeningsRepo.saveAndFlush(screenings));
    }
}
