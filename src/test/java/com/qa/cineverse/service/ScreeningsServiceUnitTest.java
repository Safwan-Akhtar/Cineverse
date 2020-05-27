package com.qa.cineverse.service;

import com.qa.cineverse.domain.Screenings;
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
        this.testScreeningsWithID = new Screenings ();
        this.testScreeningsWithID.setScreeningsId (id);
        this.screeningsDTO = this.mapToDTO(testScreeningsWithID);
    }


}
