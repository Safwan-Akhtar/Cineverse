package com.qa.cineverse.controller;

import com.qa.cineverse.domain.Screenings;
import com.qa.cineverse.dto.ScreeningsDTO;
import com.qa.cineverse.service.ScreeningsService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerUnitTest {

    @InjectMocks
    private ScreeningsController screeningsController;

    @Mock
    private ScreeningsService service;

    private List<Screenings> orders;

    private Screenings testScreenings;

    private Screenings testScreeningsWithId;

    private final long id = 1L;

    private ScreeningsDTO screeningsDTO;

    private final ModelMapper mapper = new ModelMapper();

    private ScreeningsDTO mapToDTO(Screenings screenings) { return this.mapper.map(screenings, ScreeningsDTO.class); }

}
