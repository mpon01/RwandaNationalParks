package com.mer.RWANDAPARKS.controller;

import com.mer.RWANDAPARKS.model.Animal;
import com.mer.RWANDAPARKS.model.Park;
import com.mer.RWANDAPARKS.service.IAnimalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

@Controller
public class AnimalController {
    @Value("${uploadDir}")
    private String uploadFolder;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IAnimalService animalService;

    @GetMapping(value = "/admin/add-animal")
    public String getSaveAnimalPage(Model model){
        Animal animal = new Animal();
        model.addAttribute("animal", animal);
        return "admin/addAnimal";
    }
    @PostMapping("/admin/add-animal")
    public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("commonName") String commonName,
                                                         @RequestParam("scientificName") String scientificName,
                                                         @RequestParam("population") Long population,
                                                         @RequestParam("commonName") String parkName,
                                                         Model model, HttpServletRequest request
            , final @RequestParam("image") MultipartFile file) {
        try {
            String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
            log.info("uploadDirectory:: " + uploadDirectory);
            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory, fileName).toString();
            log.info("FileName: " + file.getOriginalFilename());
            if (fileName == null || fileName.contains("..")) {
                model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
                return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
            }
            String[] cName = commonName.split(",");
            String[] sName = scientificName.split(",");
            String[] pName = parkName.split(",");
            log.info("Name: " + cName[0]+" "+filePath);
            log.info("Scientific Name: " + sName[0]);
            log.info("Population: " + population);
            log.info("Park Name: " + pName[0]);
            try {
                File dir = new File(uploadDirectory);
                if (!dir.exists()) {
                    log.info("Folder Created");
                    dir.mkdirs();
                }
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                log.info("in catch");
                e.printStackTrace();
            }
            byte[] imageData = file.getBytes();
            Animal animal = new Animal();
            animal.setAnimalName(cName[0]);
            animal.setImage(imageData);
            animal.setPopulation(population);
            animal.setScientificName(sName[0]);
            animal.setParkName(pName[0]);
            animalService.saveAnimal(animal);
            log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
            return new ResponseEntity<>("Animal saved to file - " + fileName, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Exception: " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//    @PostMapping(value = "/admin/add-animal")
//    public String saveAnimal(@ModelAttribute Animal animal, @RequestParam("photo") MultipartFile multipartFile) {
//        if (!multipartFile.isEmpty()) {
//            try {
//                byte[] photo = multipartFile.getBytes();
//                animal.setFileName(multipartFile.getOriginalFilename());
//                animal.setPhoto(photo);
//                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                String currentPrincipalName = authentication.getName();
//                animal.setAnimalName(currentPrincipalName);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        animalService.saveAnimal(animal, multipartFile);
//        return "redirect:/admin/home";
//    }

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
