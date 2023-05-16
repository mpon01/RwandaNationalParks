package com.mer.RWANDAPARKS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Common Name cannot be empty")
    @Column(name = "animalName", nullable = false)
    private String animalName;

    @NotNull(message = "Scientific Name cannot be empty")
    @Column(name = "scientificName", nullable = false, unique = true)
    private String scientificName;

    @NotNull(message = "Population cannot be empty")
    @Column(name = "population", nullable = false)
    private long population;

    @NotNull(message = "Park Name cannot be empty")
    @Column(name = "parkName", nullable = false)
    private String parkName;

    @Transient
    private MultipartFile animalPhoto;
    @Lob
    @Column(name = "animalPhoto", columnDefinition = "mediumblob")
    private byte[] photo;

    @Column(name = "fileName")
    private String fileName;


    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate createdAt;

    @Column(nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate updatedAt;

}
