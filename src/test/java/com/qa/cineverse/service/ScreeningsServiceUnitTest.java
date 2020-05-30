package com.qa.cineverse.service;

import com.qa.cineverse.domain.Screenings;
import com.qa.cineverse.dto.ScreeningsDTO;
import com.qa.cineverse.exception.ScreeningsNotFoundException;
import com.qa.cineverse.repo.ScreeningsRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ScreeningsServiceUnitTest {

    @InjectMocks
    private ScreeningsService service;

    @Mock
    private ScreeningsRepo repository;

    @Mock
    private ModelMapper mapper;

    private List<Screenings> screeningsList;

    private Screenings testScreenings;

    private final long id = 1L;

    private Screenings testScreeningsWithID;

    private ScreeningsDTO screeningsDTO;

    private LocalDateTime date;

    private ScreeningsDTO mapToDTO(Screenings screenings){
        return this.mapper.map(screenings, ScreeningsDTO.class);
    }

    @Before
    public void setUp(){
        date = LocalDateTime.of(LocalDate.ofEpochDay(2007-12-3), LocalTime.MIN);
        this.screeningsList = new ArrayList<> ();
        this.testScreenings = new Screenings (date, 1L, "deluxe", "Guardians of the Galaxy");
        this.screeningsList.add(testScreenings);
        this.testScreeningsWithID = new Screenings (testScreenings.getMovieDateTime (), testScreenings.getScreenNumber(), testScreenings.getScreenType(), testScreenings.getMovieName());
        this.testScreeningsWithID.setScreeningsId (id);
        this.screeningsDTO = this.mapToDTO(testScreeningsWithID);
    }

    @Test
    public void getAllScreeningsTest(){
        when(repository.findAll()).thenReturn(this.screeningsList);
        when(this.mapper.map(testScreeningsWithID, ScreeningsDTO.class)).thenReturn(screeningsDTO);
        assertFalse("Service returned no Screenings", this.service.readScreenings().isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void createScreeningsTest(){
        when(repository.save(testScreenings)).thenReturn(testScreeningsWithID);
        when(this.mapper.map(testScreeningsWithID, ScreeningsDTO.class)).thenReturn(screeningsDTO);
        assertEquals(this.service.createScreening (testScreenings), this.screeningsDTO);
        verify(repository, times(1)).save(this.testScreenings);
    }

    @Test
    public void findScreeningsByIdTest(){
        when(this.repository.findById(id)).thenReturn(java.util.Optional.ofNullable(testScreeningsWithID));
        when(this.mapper.map(testScreeningsWithID, ScreeningsDTO.class)).thenReturn(screeningsDTO);
        assertEquals(this.service.findScreeningsById (this.id), screeningsDTO);
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void deleteScreeningsByExistingId(){
        when(this.repository.existsById(id)).thenReturn(true, false);
        assertFalse(service.deleteScreening(id));
        verify(repository, times(1)).deleteById(id);
        verify(repository, times(2)).existsById(id);
    }

    @Test(expected = ScreeningsNotFoundException.class)
    public void deleteScreeningsByNonExistingId(){
        when(this.repository.existsById(id)).thenReturn(false);
        service.deleteScreening (id);
        verify(repository, times(1)).existsById(id);
    }
}
