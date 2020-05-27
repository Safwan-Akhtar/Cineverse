package com.qa.cineverse.service;


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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScreeningsServiceIntegrationTest {

    @Autowired
    private ScreeningsService service;

    @Autowired
    private ScreeningsRepo repository;

    @Autowired
    private ModelMapper mapper;

    private Screenings testScreenings;

    private Screenings testScreeningsWithID;

    private ScreeningsDTO mapToDTO(Screenings screenings){
        return this.mapper.map(screenings, ScreeningsDTO.class);
    }

    @Before
    public void setUp(){
        this.testScreenings = new Screenings (null, "deluxe");
        this.repository.deleteAll();
        this.testScreeningsWithID = this.repository.save(this.testScreenings);
    }

    @Test
    public void readScreeningsTest(){
        assertThat(this.service.readScreenings())
                .isEqualTo(
                        Stream.of(this.mapToDTO(testScreeningsWithID)).collect(Collectors.toList())
                );
    }

    @Test
    public void createScreeningsTest(){
        assertEquals(this.mapToDTO(this.testScreeningsWithID), this.service.createScreening (testScreenings));
    }

    @Test
    public void findCustomersByIdTest(){
        assertThat(this.service.findScreeningsById (this.testScreeningsWithID.getScreeningsId ())).isEqualTo(this.mapToDTO(this.testScreeningsWithID));
    }

    @Test
    public void deleteCustomersTest(){
        assertThat(this.service.deleteScreening(this.testScreeningsWithID.getScreeningsId ())).isFalse();
    }

}
