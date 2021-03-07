package edu.ait.winesmanager.controllers;

import edu.ait.winesmanager.dao.WineDAO;
import edu.ait.winesmanager.dto.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1")
public class WineController {

    @Autowired
    WineDAO wineDAO;

    @GetMapping("wines")
    public List<Wine> getAllWines(){
        return wineDAO.findAll();
    }

    @GetMapping("wines/{wineId}")
    public Optional<Wine> getWineById(@PathVariable("wineId") int param){
        return wineDAO.findById(param);
    }

    @DeleteMapping("wines/{wineId}")
    public void deleteWineById(@PathVariable int wineId){
        wineDAO.deleteWine(wineId);
    }

    @PostMapping("wines/")
    public ResponseEntity createWine(@RequestBody Wine newWine){
        //add the wine
        wineDAO.createWine(newWine);
        //create the location header
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}")
                .buildAndExpand(newWine.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("wines/")
    public ResponseEntity updateWine(@RequestBody Wine newWine){

        //update the wine
        boolean updated = wineDAO.updateWine(newWine);
        if(updated)
        {
            //just return 200 ok
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            //create the location header
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("{id}")
                    .buildAndExpand(newWine.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }
}

/*
@RestController
@RequestMapping("api/v1")
public class WineController {

    @Autowired
    WineDAO wineDAO;

    @GetMapping("wines")
    public List<Wine> getAllWines(){
        return wineDAO.findAll();
    }

    @GetMapping("wines/{wineId}")
    public Optional<Wine> getWineById(@PathVariable("wineId") int param){
        return wineDAO.findById(param);
    }

    @DeleteMapping("wines/{wineId}")
    public void deleteWineById(@PathVariable int wineId){
        wineDAO.deleteWine(wineId);
    }

    @PostMapping("wines/")
    public ResponseEntity createWine(@RequestBody Wine newWine){
        //add the wine
        wineDAO.createWine(newWine);
        //create the location header
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}")
                .buildAndExpand(newWine.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("wines/")
    public ResponseEntity updateWine(@RequestBody Wine newWine){

        //update the wine
        boolean updated = wineDAO.updateWine(newWine);
        if(updated)
        {
            //just return 200 ok
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            //create the location header
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("{id}")
                    .buildAndExpand(newWine.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }
}
*/
