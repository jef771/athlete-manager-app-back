package com.angularexample.angularcrud.controller;

import com.angularexample.angularcrud.error.AthleteNotFoundException;
import com.angularexample.angularcrud.model.Athlete;
import com.angularexample.angularcrud.model.Gender;
import com.angularexample.angularcrud.repository.AthleteRepository;
import com.angularexample.angularcrud.service.AthleteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AthleteController.class)
class AthleteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AthleteService athleteService;

    private Athlete athlete;

    @BeforeEach
    void setUp() {

        this.athlete = Athlete.builder()
                .athleteName("Rebeca Andrade")
                .athleteCommitee("BRA")
                .athleteDob(LocalDate.parse("1999-05-08"))
                .athleteGender(Gender.FEMALE)
                .athleteSport("Artistic Gymnastics")
                .athleteEmail("rebeca.andrade1@cob.gov.br")
                .imageUrl("images/image.jpg")
                .athleteId(1L)
                .build();
    }

    @Test
    void saveAthlete() throws Exception {


        Mockito.doNothing().when(athleteService).saveAthlete(this.athlete);

        mockMvc.perform(post("http://localhost:8080/athletes/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"athleteGender\":\"FEMALE\",\n" +
                        "\t\"athleteName\":\"Rebeca Andrade\",\n" +
                        "\t\"athleteSport\":\"Artistic Gymnastics\",\n" +
                        "\t\"athleteCommitee\":\"BRA\",\n" +
                        "\t\"athleteDob\":\"1999-05-01\",\n" +
                        "\t\"imageUrl\":\"images/image.jpg\",\n" +
                        "\t\"athleteEmail\":\"rebeca.andrade1@cob.gov.br\"\n" +
                        "}")).andExpect(status().isCreated());
    }

    @Test
    void fetchAthleteById() throws Exception {
        Mockito.when(athleteService.fetchAthleteById(1L))
                .thenReturn(this.athlete);

        mockMvc.perform(get("http://localhost:8080/athletes/fetch/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.athleteName").value(this.athlete.getAthleteName()));
    }

}