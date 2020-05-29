package com.qa.cineverse.service;

import com.qa.cineverse.domain.Screenings;
import com.qa.cineverse.dto.ScreeningsDTO;
import com.qa.cineverse.repo.ScreeningsRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    private Screenings testScreeningsWithVariable;

    private LocalDateTime date;

    private ScreeningsDTO mapToDTO(Screenings screenings){
        return this.mapper.map(screenings, ScreeningsDTO.class);
    }

    @Before
    public void setUp(){
        date = LocalDateTime.of(LocalDate.ofEpochDay(2007-12-3), LocalTime.MIN);
        this.testScreenings = new Screenings (1L, date, 1L, "deluxe", "Guardians of the Galaxy");
        this.repository.deleteAll();
        this.testScreeningsWithVariable = this.repository.save(this.testScreenings);
    }

    @Test
    public void readScreeningsTest(){
        assertThat(this.service.readScreenings())
                .isEqualTo(
                        Stream.of(this.mapToDTO(testScreeningsWithVariable)).collect(Collectors.toList())
                );
    }

    @Test
    public void readScreeningsByNameTest(){
        assertThat(this.service.readScreeningsByName("Guardians of the Galaxy"))
                .isEqualTo(
                        Stream.of(this.mapToDTO(testScreeningsWithVariable)).collect(Collectors.toList())
                );
    }

    @Test
    public void findCustomersByIdTest(){
        assertThat(this.service.findScreeningsById (this.testScreeningsWithVariable.getScreeningsId ())).isEqualTo(this.mapToDTO(this.testScreeningsWithVariable));
    }

    @Test
    public void deleteCustomersTest(){
        assertThat(this.service.deleteScreening(this.testScreeningsWithVariable.getScreeningsId ())).isFalse();
    }

}
