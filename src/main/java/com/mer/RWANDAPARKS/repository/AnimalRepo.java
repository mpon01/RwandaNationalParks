package com.mer.RWANDAPARKS.repository;

import com.mer.RWANDAPARKS.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Integer> {

    @Query("SELECT ani FROM Animal ani WHERE ani.animalName LIKE ?1")
    List<Animal> getAnimalByName(String name);
}
