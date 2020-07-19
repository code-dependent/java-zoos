package local.jlwilliams.zoos.controllers;

import local.jlwilliams.zoos.services.AnimalService;
import local.jlwilliams.zoos.views.AnimalCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/animals")
public class AnimalController
{
    @Autowired
    private AnimalService animalService;

    @GetMapping(value = "/count")
    public ResponseEntity<?> listAnimalCount(){

        List<AnimalCount> rtn = animalService.listAnimalCount();

        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }



}
