package edu.ait.winesmanager.controllers;

import edu.ait.winesmanager.dao.WineDAO;
import edu.ait.winesmanager.dto.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
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
}
