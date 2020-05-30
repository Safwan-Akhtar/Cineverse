package com.qa.cineverse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.cineverse.domain.Screenings;
import com.qa.cineverse.dto.ScreeningsDTO;
import com.qa.cineverse.repo.ScreeningsRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ScreeningsControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private ScreeningsRepo repository;

    @Autowired
    private ModelMapper mapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Screenings testScreenings;

    private Screenings testScreeningsWithID;

    private long id;

    private String name;

    private ScreeningsDTO screeningsDTO;

    private LocalDateTime date;

    private ScreeningsDTO mapToDTO(Screenings screenings){
        return this.mapper.map(screenings, ScreeningsDTO.class);
    }

    @Before
    public void setUp(){
        date = LocalDateTime.of(LocalDate.ofEpochDay(2007-12-3), LocalTime.MIN);
        this.repository.deleteAll();
        this.testScreenings = new Screenings(date, 1L, "deluxe", "Guardians of the Galaxy");
        this.testScreeningsWithID = this.repository.save(testScreenings);
        this.id = testScreeningsWithID.getScreeningsId ();
        this.name = testScreeningsWithID.getMovieName();
        this.screeningsDTO = this.mapToDTO(testScreeningsWithID);
    }

    @Test
    public void getAllScreeningsTest() throws Exception {
        List<ScreeningsDTO> screeningsDTOList = new ArrayList<> ();
        screeningsDTOList.add(screeningsDTO);
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getAllScreenings")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(screeningsDTOList));
    }

    @Test
    public void getAllScreeningsByNameTest() throws Exception {
        List<ScreeningsDTO> screeningsDTOList = new ArrayList<> ();
        screeningsDTOList.add(screeningsDTO);
        String content = this.mock.perform(
                request(HttpMethod.GET, "/readScreeningsByName/" + this.name)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(screeningsDTOList));
    }

    @Test
    public void getScreeningsByID() throws Exception {
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getScreeningById/" + this.id)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(screeningsDTO));
    }

    @Test
    public void createScreeningsTest() throws Exception {
        String result = this.mock.perform(
                request(HttpMethod.POST, "/createScreening")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testScreenings))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(result, this.objectMapper.writeValueAsString(screeningsDTO));
    }

    @Test
    public void deleteScreeningsTest() throws Exception {
        this.mock.perform(
                request(HttpMethod.DELETE, "/deleteScreening/" + this.id)
        ).andExpect(status().isNoContent());
    }
}
