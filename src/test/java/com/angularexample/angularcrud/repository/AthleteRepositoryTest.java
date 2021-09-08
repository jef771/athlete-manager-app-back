package com.angularexample.angularcrud.repository;

import com.angularexample.angularcrud.model.Athlete;
import com.angularexample.angularcrud.model.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class AthleteRepositoryTest {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Athlete athlete =
                Athlete.builder()
                        .athleteName("Rebeca Andrade")
                        .athleteDob(LocalDate.of(1999,8,5))
                        .athleteCommitee("BRA")
                        .athleteEmail("rebeca.andrade@cob.gov.br")
                        .athleteGender(Gender.FEMALE)
                        .athleteSport("Artistc Gymnastics")
                        .build();

        entityManager.persist(athlete);
    }

    @Test
    public void whenFindById_thenReturnAthlete() {
        Athlete athlete = athleteRepository.findById(1L).get();
        assertEquals(athlete.getAthleteName(), "Rebeca Andrade");
    }
}