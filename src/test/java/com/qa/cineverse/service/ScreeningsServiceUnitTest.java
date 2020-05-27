package com.qa.cineverse.service;

import com.qa.cineverse.domain.Screenings;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.dto.ScreeningsDTO;
import com.qa.cineverse.repo.ScreeningsRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

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

    private ScreeningsDTO mapToDTO(Screenings screenings){
        return this.mapper.map(screenings, ScreeningsDTO.class);
    }

    @Before
    public void setUp(){
        this.screeningsList = new ArrayList<> ();
        this.testScreenings = new Screenings (1L, null, "deluxe");
        this.screeningsList.add(testScreenings);
        this.testScreeningsWithID = new Screenings (testScreenings.getMovieDateTime (), testScreenings.getScreenType ());
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

}
