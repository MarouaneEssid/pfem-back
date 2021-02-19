package com.talan.pfemanager.service;

import java.util.List;

import com.talan.pfemanager.dto.SubjectDTO;
import com.talan.pfemanager.entity.Subject;

public interface SubjectService {
	
	String  save(SubjectDTO subjectDto);
	List<SubjectDTO> findAll();
	SubjectDTO findById(int id);
	String deleteSubjectById(int id);
	String updateSubject(SubjectDTO subjectdto, int id);	
	String deleteAll();
	List<Subject> findByTechnologiesIdIn(List<Integer> ids);
}
