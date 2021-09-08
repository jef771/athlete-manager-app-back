package com.angularexample.angularcrud.service;

import com.angularexample.angularcrud.error.AthleteNotFoundException;
import com.angularexample.angularcrud.model.Athlete;

import java.util.List;

public interface AthleteService {

    void saveAthlete(Athlete athlete);

    void deleteAthlete(Long id);

    List<Athlete> findAllAthletes();

    Athlete fetchAthleteById(Long athleteId) throws AthleteNotFoundException;

    Athlete fetchAthleteByEmail(String athleteEmail) throws AthleteNotFoundException;

    Athlete updateAthlete(Long athleteId, Athlete athlete) throws AthleteNotFoundException;
}
