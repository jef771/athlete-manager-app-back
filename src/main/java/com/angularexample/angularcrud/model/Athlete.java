package com.angularexample.angularcrud.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Athlete implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, updatable = false)
    private Long athleteId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender athleteGender;
    @NotBlank
    private String athleteName;
    @NotBlank
    private String athleteSport;
    @NotBlank
    private String athleteCommitee;
    @Column(nullable = false)
    private LocalDate athleteDob;
    private String imageUrl;
    @NotBlank
    @Column(unique = true)
    private String athleteEmail;

    @Override
    public String toString() {
        return "Athlete{" +
                "athleteId=" + athleteId +
                ", athleteGender=" + athleteGender +
                ", athleteName='" + athleteName + '\'' +
                ", athleteSport='" + athleteSport + '\'' +
                ", athleteCommitee='" + athleteCommitee + '\'' +
                ", athleteDob=" + athleteDob +
                ", imageUrl='" + imageUrl + '\'' +
                ", athleteEmail='" + athleteEmail + '\'' +
                '}';
    }
}
