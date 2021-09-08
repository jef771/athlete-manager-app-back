package com.angularexample.angularcrud.config;


import com.angularexample.angularcrud.model.Athlete;
import com.angularexample.angularcrud.model.Gender;
import com.angularexample.angularcrud.repository.AthleteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class AthleteConfig {

    @Bean
    CommandLineRunner commandLineRunner(AthleteRepository repository) {
        return args -> {
            Athlete rebeca = Athlete.builder()
                    .athleteName("Rebeca Rodrigues de Andrade")
                    .athleteDob(LocalDate.of(1999,8,5))
                    .athleteCommitee("BRA")
                    .athleteEmail("rebeca.andrade@cob.gov.br")
                    .athleteGender(Gender.FEMALE)
                    .athleteSport("Artistc Gymnastics")
                    .imageUrl("https://www.cob.org.br/pt/img/atleta/foto/998/principal/")
                    .build();

            Athlete rayssa = Athlete.builder()
                    .athleteName("Jhulia Rayssa Mendes Leal")
                    .athleteDob(LocalDate.of(2008,1,4))
                    .athleteCommitee("BRA")
                    .athleteEmail("rayssa.leal@cob.gov.br")
                    .athleteGender(Gender.FEMALE)
                    .athleteSport("Skate")
                    .imageUrl("https://www.cob.org.br/pt/img/atleta/foto/3510/principal/")
                    .build();

            Athlete isaquias = Athlete.builder()
                    .athleteName("Isaquias Queiroz dos Santos")
                    .athleteDob(LocalDate.of(1994,3,1))
                    .athleteCommitee("BRA")
                    .athleteEmail("isaquias.queiroz@cob.gov.br")
                    .athleteGender(Gender.MALE)
                    .athleteSport("Canoe Sprint")
                    .imageUrl("https://gerenciador.cob.org.br/arquivos/integrantes/30042019120708isaquias.jpg")
                    .build();

            repository.saveAll(List.of(rebeca, rayssa, isaquias));
        };
    }
}
