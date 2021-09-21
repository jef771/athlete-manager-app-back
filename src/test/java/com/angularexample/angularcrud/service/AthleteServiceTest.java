package com.angularexample.angularcrud.service;

import com.angularexample.angularcrud.error.AthleteNotFoundException;
import com.angularexample.angularcrud.model.Athlete;
import com.angularexample.angularcrud.model.Gender;
import com.angularexample.angularcrud.repository.AthleteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AthleteServiceTest {

    @Autowired
    private AthleteService athleteService;

    @MockBean
    private AthleteRepository athleteRepository;

    @BeforeEach
    void setUp() {
        Athlete athlete =
                Athlete.builder()
                        .athleteName("Rebeca Andrade")
                        .athleteCommitee("BRA")
                        .athleteDob(LocalDate.parse("1999-05-08"))
                        .athleteGender(Gender.FEMALE)
                        .athleteSport("Artistic Gymnastics")
                        .athleteEmail("rebeca.andrade1@cob.gov.br")
                        .athleteId(1L)
                        .build();

        Mockito.when(athleteRepository.findAthleteByEmail("rebeca.andrade1@cob.gov.br"))
                .thenReturn(java.util.Optional.ofNullable(athlete));

        Mockito.when(athleteRepository.findById(1L))
                .thenReturn(java.util.Optional.ofNullable(athlete));
    }

    @Test
    @DisplayName("Get Athlete on valid email")
    public void whenValidEmail_thenAthleteShouldFound() throws AthleteNotFoundException {
        String email = "rebeca.andrade1@cob.gov.br";

        Athlete found = athleteService.fetchAthleteByEmail(email);

        assertEquals(email, found.getAthleteEmail());
    }

    @Test
    @DisplayName("Dont Get Athlete on invalid email")
    public void whenInvalidEmail_thenAthleteShouldNotFound() throws AthleteNotFoundException {
        String email = "rebeca.andrade@cob.gov.br";

        Athlete found = athleteService.fetchAthleteByEmail("rebeca.andrade1@cob.gov.br");

        assertNotEquals(email, found.getAthleteEmail());
    }

    @Test
    @DisplayName("Update Athlete on valid id")
    public void wheValidId_thenAthleteShouldUpdate() throws AthleteNotFoundException {
        Long id = 1L;

        Athlete found = athleteService.fetchAthleteById(id);

        found.setAthleteEmail("rebeca.andrade@cob.gov.br");

        athleteService.saveAthlete(found);

        found = athleteService.fetchAthleteById(id);

        assertEquals("rebeca.andrade@cob.gov.br", found.getAthleteEmail());
    }

    @Test
    @DisplayName("Dont Get Athlete on invalid email")
    public void whenInvalidId_thenShouldThrowError() throws AthleteNotFoundException {

        Athlete found = athleteService.fetchAthleteByEmail("rebeca.andrade1@cob.gov.br");

        Throwable exc = assertThrows(AthleteNotFoundException.class, () -> athleteService.fetchAthleteById(2L));

        assertEquals("Athlete with id 2 could not be found", exc.getMessage());

    }



}
