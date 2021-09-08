package com.angularexample.angularcrud.service;

import com.angularexample.angularcrud.error.AthleteNotFoundException;
import com.angularexample.angularcrud.model.Athlete;
import com.angularexample.angularcrud.repository.AthleteRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AthleteServiceImpl implements AthleteService{

    private final AthleteRepository athleteRepository;

    public AthleteServiceImpl(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }

    @Override
    public void saveAthlete(Athlete athlete) {

        Optional<Athlete> athleteOptional = this.athleteRepository
                .findAthleteByEmail(athlete.getAthleteEmail());

        if(athleteOptional.isPresent()) {
            throw new IllegalStateException("Athlete already registered");
        }
        this.athleteRepository.save(athlete);
    }

    @Override
    public void deleteAthlete(Long athleteId) {
        athleteRepository.deleteById(athleteId);
    }

    @Override
    public List<Athlete> findAllAthletes() {
        List<Athlete> athletes = athleteRepository.findAll();

        athletes.sort(Comparator.comparing(Athlete::getAthleteName));

        return athletes;
    }

    @Override
    public Athlete fetchAthleteById(Long athleteId) throws AthleteNotFoundException {

        Optional<Athlete> athleteOptional = this.athleteRepository
                .findById(athleteId);

        if(athleteOptional.isEmpty()) {
            throw new AthleteNotFoundException("Athlete "
                    + "with id "
                    + athleteId
                    + " could not be found");
        }
        return athleteRepository.findById(athleteId).get();
    }

    @Override
    public Athlete fetchAthleteByEmail(String athleteEmail) throws AthleteNotFoundException {

        Optional<Athlete> athleteOptional = this.athleteRepository
                .findAthleteByEmail(athleteEmail);

        if(athleteOptional.isEmpty()) {
            throw new AthleteNotFoundException("Athlete "
                    + "with email "
                    + athleteEmail
                    + " could not be found");
        }

        return athleteRepository.findAthleteByEmail(athleteEmail).get();
    }

    @Override
    public Athlete updateAthlete(Long athleteId, Athlete athlete) throws AthleteNotFoundException {

        Optional<Athlete> athleteOptional = this.athleteRepository
                .findById(athleteId);

        if(athleteOptional.isEmpty()) {
            throw new AthleteNotFoundException("Athlete "
                    + "with email "
                    + athleteId
                    + " could not be found");
        }

        Athlete athleteDB = this.athleteRepository.findById(athleteId).get();

        if(Objects.nonNull(athlete.getAthleteName()) &&
            !"".equalsIgnoreCase(athlete.getAthleteName())) {
            athleteDB.setAthleteName(athlete.getAthleteName());
        }

        if(Objects.nonNull(athlete.getAthleteGender())) {
            athleteDB.setAthleteGender(athlete.getAthleteGender());
        }

        if(Objects.nonNull(athlete.getAthleteSport()) &&
                !"".equalsIgnoreCase(athlete.getAthleteSport())) {
            athleteDB.setAthleteSport(athlete.getAthleteSport());
        }

        if(Objects.nonNull(athlete.getAthleteEmail()) &&
                !"".equalsIgnoreCase(athlete.getAthleteEmail())) {
            athleteDB.setAthleteEmail(athlete.getAthleteEmail());
        }

        if(Objects.nonNull(athlete.getImageUrl()) &&
                !"".equalsIgnoreCase(athlete.getImageUrl())) {
            athleteDB.setImageUrl(athlete.getImageUrl());
        }

        if(Objects.nonNull(athlete.getAthleteDob())) {
            athleteDB.setAthleteDob(athlete.getAthleteDob());
        }

        if(Objects.nonNull(athlete.getAthleteCommitee()) &&
                !"".equalsIgnoreCase(athlete.getAthleteCommitee())) {
            athleteDB.setAthleteCommitee(athlete.getAthleteCommitee());
        }

        return athleteRepository.save(athleteDB);
    }
}
