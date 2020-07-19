package local.jlwilliams.zoos.controllers;

import local.jlwilliams.zoos.models.Zoo;
import local.jlwilliams.zoos.services.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/zoos")
public class ZooController
{
    @Autowired
    private ZooService zooService;
    @GetMapping(value = "/zoos")
    public ResponseEntity<?> listZoos(){
        List<Zoo> rtn = zooService.listAllZoos();

        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }

    @GetMapping(value = "/zoo/{id}")
    public ResponseEntity<?> getZooById(@PathVariable long id){
        return new ResponseEntity<>(zooService.findZooById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/zoo", consumes = {"application/json"})
    public ResponseEntity<?> postZoo(@Valid @RequestBody Zoo newzoo){
        newzoo.setZooid(0);
        newzoo = zooService.save(newzoo);

        HttpHeaders responseHeaders = new HttpHeaders();

        URI newzooURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{zooid}")
                .buildAndExpand(newzoo.getZooid())
                .toUri();

        responseHeaders.setLocation(newzooURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/zoo/{id}", consumes = {"application/json"})
    public ResponseEntity<?> putZoo(@Valid @RequestBody Zoo updatezoo, @PathVariable long id){
        updatezoo.setZooid(id);
        zooService.save(updatezoo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "zoo/{id}", consumes = {"application/json"})
    public ResponseEntity<?> patchZoo(@RequestBody Zoo zoo, @PathVariable long id){
        zooService.update(zoo, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
