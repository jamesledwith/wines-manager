package edu.ait.winesmanager.controllers;

import edu.ait.winesmanager.dao.WineDAO;
import edu.ait.winesmanager.dto.Wine;
import edu.ait.winesmanager.exceptions.WineNotFoundException;
import edu.ait.winesmanager.repositories.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    WineRepository wineRepository;

    @GetMapping("wines")
    public List<Wine> getAllWines(){
        return wineRepository.findAll();
    }

    @GetMapping("wines/{wineId}")
    public Wine getWineById(@PathVariable("wineId") int wineId){
        Optional<Wine> foundWine = wineRepository.findById(wineId);
        if(foundWine.isPresent())
            return foundWine.get();
        else
            throw new WineNotFoundException("Unable to retrieve wine with id:" + wineId);
    }

    @DeleteMapping("wines/{wineId}")
    public void deleteWineById(@PathVariable int wineId){
        try{
            wineRepository.deleteById(wineId);
        }
        catch (EmptyResultDataAccessException e){
            throw new WineNotFoundException("Unable to delete wine by id:" + wineId);
        }
    }

    @PostMapping("wines/")
    public ResponseEntity createWine(@RequestBody Wine newWine){
        //add the wine
        Wine savedWine = wineRepository.save(newWine);
        //create the location header
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}")
                .buildAndExpand(savedWine.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("wines/")
    public ResponseEntity updateWine(@RequestBody Wine newWine)
    {
        if(newWine.getId() != null)
        {
            //Save the Wine
            wineRepository.save(newWine);
            //return 200 ok
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else
            {
            //create new wine
            Wine savedWine = wineRepository.save(newWine);
            //create the location header
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("{id}")
                    .buildAndExpand(savedWine.getId()).toUri();
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
