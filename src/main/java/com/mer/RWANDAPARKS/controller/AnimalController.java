package com.mer.RWANDAPARKS.controller;

import com.mer.RWANDAPARKS.model.Animal;
import com.mer.RWANDAPARKS.model.Park;
import com.mer.RWANDAPARKS.service.IAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
public class AnimalController {
    @Autowired
    private IAnimalService animalService;

    @GetMapping(value = "/admin/add-animal")
    public String getSaveAnimalPage(Model model){
        Animal animal = new Animal();
        model.addAttribute("animal", animal);
        return "admin/addAnimal";
    }

    @PostMapping(value = "/admin/add-animal")
    public String saveAnimal(@ModelAttribute Animal animal, @RequestParam("photo") MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            try {
                byte[] photo = multipartFile.getBytes();
                animal.setFileName(multipartFile.getOriginalFilename());
                animal.setPhoto(photo);
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String currentPrincipalName = authentication.getName();
                animal.setAnimalName(currentPrincipalName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        animalService.saveAnimal(animal, multipartFile);
        return "redirect:/admin/home";
    }

    @GetMapping(value = "/admin/edit-animal")
    public String getEditAnimalPage(@RequestParam("id") int id, Model theModel) {
        Optional<Animal> animal = animalService.getAnimalById(id);
        theModel.addAttribute("animal", animal);
        return "admin/editAnimal";
    }
    @GetMapping(value = "/admin/delete-animal")
    public String getDeleteAnimalPage(@RequestParam("id") int id, Model theModel) {
        Optional<Animal> animal = animalService.getAnimalById(id);
        theModel.addAttribute("animal", animal);
        return "admin/deleteAnimal";
    }
    @PostMapping(value = "/admin/delete-animal")
    public String deleteAnimal(@RequestParam("id") int id) {
        animalService.deleteAnimal(id);
        return "redirect:/admin/home";
    }
}
