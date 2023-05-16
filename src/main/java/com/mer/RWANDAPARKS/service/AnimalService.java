package com.mer.RWANDAPARKS.service;

import com.mer.RWANDAPARKS.model.Animal;
import com.mer.RWANDAPARKS.repository.AnimalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService implements IAnimalService{
    @Autowired
    private AnimalRepo animalRepo;

    @Override
    public Animal saveAnimal(Animal animal, MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            String contentType = multipartFile.getContentType();
            if (contentType.equals("image/jpeg") || contentType.equals("image/jpg") || contentType.equals("image/png")) {
                try {
                    byte[] photo = multipartFile.getBytes();
                    animal.setPhoto(photo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                throw new IllegalArgumentException("Only JPG/JPEG and PNG photos allowed.");
            }
        }
        return animalRepo.save(animal);
    }

    @Override
    public Animal editAnimal(Animal animal, MultipartFile multipartFile) {
        return null;
    }

    @Override
    public void deleteAnimal(int id) {
        animalRepo.deleteById(id);
    }

    @Override
    public Optional<Animal> getAnimalById(int id) {
        return  animalRepo.findById(id);
    }

    @Override
    public List<Animal> allAnimals() {
        return animalRepo.findAll();
    }

    @Override
    public List<Animal> searchAnimalByName(String name) {
        return animalRepo.getAnimalByName(name);
    }
}
