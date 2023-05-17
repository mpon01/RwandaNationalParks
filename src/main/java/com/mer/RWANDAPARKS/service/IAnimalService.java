package com.mer.RWANDAPARKS.service;

import com.mer.RWANDAPARKS.model.Animal;
import com.mer.RWANDAPARKS.model.Park;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IAnimalService {

    void saveAnimal(Animal animal);
//    Animal saveAnimal(Animal animal, MultipartFile multipartFile);
    Animal editAnimal(Animal animal, MultipartFile multipartFile);

    void deleteAnimal(int id);
    Optional<Animal> getAnimalById(int id);
    List<Animal> allAnimals();
    @Query("SELECT m FROM Animal m WHERE m.animalName LIKE ?1")
    List<Animal> searchAnimalByName(String name);

//    Page<Animal> getPageAnimal(Pageable pageable);

//    Page<Animal> getAnimalByName(String name, Pageable pageable);
}
