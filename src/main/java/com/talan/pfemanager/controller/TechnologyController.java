package com.talan.pfemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.talan.pfemanager.dto.TechnologyDTO;
import com.talan.pfemanager.service.TechnologyService;

@RestController
public class TechnologyController {
	
	@Autowired
	TechnologyService technologyService;
	
    @GetMapping("/technologies/all")
    public List<TechnologyDTO> getTEchnologies (){
    	return technologyService.getTechnologies();
    }
    
    @GetMapping("/technologies/{id}")
    public TechnologyDTO getTechnology (@PathVariable int id) {
    	return technologyService.getTechnology(id);
    }
    
    @PostMapping("/technologies")
    public TechnologyDTO addTechnology  (@RequestBody TechnologyDTO technologyDTO) {
    	return technologyService.addTechnology(technologyDTO);
    }
    
    @DeleteMapping("technologies/{id}")
    public void deleteTechnology (@PathVariable int id) {
    	technologyService.deleteTechnology(id);
    }

    @DeleteMapping("technologies")
    public void deleteTechnologies() {
    	technologyService.deleteAllTechnologies();
    }
}
