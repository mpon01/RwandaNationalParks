package com.mer.RWANDAPARKS.controller;

import com.mer.RWANDAPARKS.model.Animal;
import com.mer.RWANDAPARKS.model.Park;
import com.mer.RWANDAPARKS.repository.AnimalRepo;
import com.mer.RWANDAPARKS.repository.ParkRepo;
import com.mer.RWANDAPARKS.service.IAnimalService;
import com.mer.RWANDAPARKS.service.IParkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    private AnimalRepo animalRepo;

    @Autowired
    private ParkRepo parkRepo;

    @Autowired
    private IAnimalService animalService;

    @Autowired
    private IParkService parkService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/admin/allParks")
    public String viewParks(Model model){
        List<Park> parks = parkRepo.findAll();
        model.addAttribute("parks", parks);
        return "admin/admin_home";
    }

    @GetMapping("/admin/allAnimals")
    public String viewAnimals(Model model){
        List<Animal> animals = animalRepo.findAll();
        model.addAttribute("animals", animals);
        return "admin/admin_home";
    }


    @GetMapping("/admin/display-image/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Animal> animals)
            throws ServletException, IOException {
        log.info("Id :: " + id);
        animals = animalService.getAnimalById(Math.toIntExact(id));
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(animals.get().getImage());
        response.getOutputStream().close();
    }
    @GetMapping("/admin/imageDetails")
    String showProductDetails(@RequestParam("id") Long id, Optional<Animal> animal, Model model) {
        try {
            log.info("Id :: " + id);
            if (id != 0) {
                animal = animalService.getAnimalById(Math.toIntExact(id));

                log.info("products :: " + animal);
                if (animal.isPresent()) {
                    model.addAttribute("id", animal.get().getId());
                    model.addAttribute("animalName", animal.get().getAnimalName());
                    model.addAttribute("scientificName", animal.get().getScientificName());
                    model.addAttribute("population", animal.get().getPopulation());
                    model.addAttribute("parkName", animal.get().getParkName());
                    return "admin/preview";
                }
                return "redirect:/admin/home";
            }
            return "redirect:/admin/home";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/home";
        }
    }


}
