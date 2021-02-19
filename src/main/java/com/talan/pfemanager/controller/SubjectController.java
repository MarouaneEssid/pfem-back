package com.talan.pfemanager.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.talan.pfemanager.dto.SubjectDTO;
import com.talan.pfemanager.entity.Subject;
import com.talan.pfemanager.service.SubjectService;

@RestController
@RequestMapping(value="/subjects")
@CrossOrigin
public class SubjectController {

	@Autowired SubjectService subjectService;
	@PostMapping("")
	public void addSubject(@RequestBody SubjectDTO subjectDTO) {
		subjectService.save(subjectDTO);
	}

    @GetMapping("/{id}")
    public SubjectDTO getSubject(@PathVariable int id) {
        return subjectService.findById(id);
    }
    @GetMapping("")
    public List<SubjectDTO> getSubjects(){
    	return subjectService.findAll();
    }
    
    @PostMapping("/search/{ids}")
    public List<Subject> search(@PathVariable List<Integer> ids){
    	return subjectService.findByTechnologiesIdIn(ids);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id) {
    	return subjectService.deleteSubjectById(id);
    }
    
    @DeleteMapping("")
    public String deleteAll() {
    	return subjectService.deleteAll();
    	}
    
    @PutMapping("/{id}")
    public String updateSubject(@RequestBody SubjectDTO subjectDTO ,@PathVariable int id) {
    	return  subjectService.updateSubject(subjectDTO, id);
    }
}
