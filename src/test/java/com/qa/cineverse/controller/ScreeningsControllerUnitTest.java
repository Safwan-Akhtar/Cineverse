package com.qa.cineverse.controller;

import com.qa.cineverse.domain.Screenings;
import com.qa.cineverse.dto.ScreeningsDTO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    private final String name = "Mulan";

    private ScreeningsDTO screeningsDTO;

    private LocalDateTime date;

    private final ModelMapper mapper = new ModelMapper();

    private ScreeningsDTO mapToDTO(Screenings screenings){
        return this.mapper.map(screenings, ScreeningsDTO.class);
    }

    @Before
    public void setUp(){
        date = LocalDateTime.of(LocalDate.ofEpochDay(2007-12-3), LocalTime.MIN);
        this.screenings = new ArrayList<> ();
        this.testScreenings = new Screenings (date, 1L, "deluxe", "Guardians of the Galaxy");
        this.screenings.add(testScreenings);
        this.testScreeningsWithId = new Screenings (testScreenings.getMovieDateTime (), testScreenings.getScreenNumber(), testScreenings.getScreenType(), testScreenings.getMovieName());
        this.testScreeningsWithId.setScreeningsId (this.id);
        this.testScreeningsWithId.setMovieName (this.name);
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

    @Test
    public void getScreeningsByIDTest(){
        when(this.service.findScreeningsById (id)).thenReturn(this.screeningsDTO);
        assertEquals(this.screeningController.getScreeningById (id), new ResponseEntity<ScreeningsDTO>(this.screeningsDTO, HttpStatus.OK));
        verify(service, times(1)).findScreeningsById (id);
    }

    @Test
    public void getScreeningsByNameTest(){
        when(service.readScreeningsByName(name)).thenReturn(this.screenings.stream().map(this::mapToDTO).collect(Collectors.toList()));
        assertFalse("No screenings found", this.screeningController.readScreeningsByName(name).getBody().isEmpty());
        verify(service, times(1)).readScreeningsByName(name);
    }



//    @GetMapping("/readScreeningsByName/{movieName}")
//    public ResponseEntity<List<ScreeningsDTO>> readScreeningsByName(@PathVariable String movieName){
//        return ResponseEntity.ok(this.service.readScreeningsByName (movieName));
//    }


}
