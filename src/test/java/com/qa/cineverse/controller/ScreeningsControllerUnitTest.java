package com.qa.cineverse.controller;

import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.domain.Screenings;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.dto.ScreeningsDTO;
import com.qa.cineverse.service.CustomersService;
import com.qa.cineverse.service.ScreeningsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ScreeningsControllerUnitTest {

    @InjectMocks
    private ScreeningsController screeningController;

    @Mock
    private ScreeningsService service;

    private List<Screenings> screenings;

    private Screenings testScreenings;

    private Screenings testScreeningsWithId;

    private final long id = 1L;

    private ScreeningsDTO screeningsDTO;

    private final ModelMapper mapper = new ModelMapper();

    private ScreeningsDTO mapToDTO(Screenings screenings){
        return this.mapper.map(screenings, ScreeningsDTO.class);
    }

    @Before
    public void setUp(){
        this.screenings = new ArrayList<> ();
        this.testScreenings = new Screenings (null, "Deluxe");
        this.screenings.add(testScreenings);
        this.testScreeningsWithId = new Screenings (testScreenings.getMovieDateTime(), testScreenings.getScreenType());
        this.testScreeningsWithId.setScreeningsId (this.id);
        this.screeningsDTO = this.mapToDTO(testScreeningsWithId);
    }

    @Test
    public void getAllScreeningsTest(){
        when(service.readScreenings ()).thenReturn(this.screenings.stream().map(this::mapToDTO).collect(Collectors.toList()));
        assertFalse("No screenings found", this.screeningController.getAllScreenings().getBody().isEmpty());
        verify(service, times(1)).readScreenings ();
    }

    @Test
    public void createScreeningsTest(){
        when(this.service.createScreening(testScreenings)).thenReturn(this.screeningsDTO);
        assertEquals(this.screeningController.createScreening(testScreenings), new ResponseEntity<ScreeningsDTO> (this.screeningsDTO, HttpStatus.CREATED));
        verify(this.service, times(1)).createScreening(testScreenings);
    }

    @Test
    public void deleteScreeningsTestFalse(){
        this.screeningController.deleteScreening(id);
        verify(service, times(1)).deleteScreening(id);
    }

}
