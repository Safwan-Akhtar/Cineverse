package com.qa.cineverse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.cineverse.domain.Customers;
import com.qa.cineverse.domain.Screenings;
import com.qa.cineverse.dto.CustomersDTO;
import com.qa.cineverse.dto.ScreeningsDTO;
import com.qa.cineverse.repo.CustomersRepo;
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

    private ScreeningsDTO screeningsDTO;

    private ScreeningsDTO mapToDTO(Screenings screenings){
        return this.mapper.map(screenings, ScreeningsDTO.class);
    }

    @Before
    public void setUp(){
        this.repository.deleteAll();
        this.testScreenings = new Screenings (null, "Deluxe");
        this.testScreeningsWithID = this.repository.save(testScreenings);
        this.id = testScreeningsWithID.getScreeningsId ();
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
}
