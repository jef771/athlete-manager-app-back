package com.angularexample.angularcrud.repository;

import com.angularexample.angularcrud.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    @Query("SELECT a FROM Athlete a WHERE a.athleteEmail = ?1")
    Optional<Athlete> findAthleteByEmail(String athleteEmail);
}
