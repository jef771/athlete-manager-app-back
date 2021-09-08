package com.angularexample.angularcrud.controller;

import com.angularexample.angularcrud.error.AthleteNotFoundException;
import com.angularexample.angularcrud.model.Athlete;
import com.angularexample.angularcrud.service.AthleteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/athletes")
public class AthleteController {

    private final AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Athlete>> fetchAthleteList() {
        List<Athlete> athletes = athleteService.findAllAthletes();
        return new ResponseEntity<>(athletes, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Athlete> saveAthlete(@RequestBody Athlete athlete) {
        this.athleteService.saveAthlete(athlete);
        return new ResponseEntity<>(athlete, HttpStatus.CREATED);
    }

    @GetMapping(path = "/fetch/{athleteId}")
    public ResponseEntity<Athlete> fetchAthleteById(@PathVariable("athleteId") Long athleteId) throws AthleteNotFoundException {
        Athlete athlete = athleteService.fetchAthleteById(athleteId);
        return new ResponseEntity<>(athlete, HttpStatus.OK);
    }

    @GetMapping(path = "/fetch-by-email/{athleteEmail}")
    public ResponseEntity<Athlete> fetchAthleteByEmail(@PathVariable("athleteEmail") String athleteEmail) throws AthleteNotFoundException {
        Athlete athlete = athleteService.fetchAthleteByEmail(athleteEmail);
        return new ResponseEntity<>(athlete, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{athleteId}")
    public ResponseEntity<?> deleteAthleteById(@PathVariable("athleteId") Long athleteID) {
        athleteService.deleteAthlete(athleteID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/update/{athleteId}")
    public ResponseEntity<Athlete> updateAthlete(@PathVariable("athleteId") Long athleteId,
                                          @RequestBody Athlete athlete) throws AthleteNotFoundException {
        athleteService.updateAthlete(athleteId, athlete);
        return new ResponseEntity<>(athlete, HttpStatus.OK);
    }
}
