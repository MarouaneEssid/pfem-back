package com.talan.pfemanager.helper;

import java.util.List;
import java.util.stream.Collectors;

import com.talan.pfemanager.dto.SubjectDTO;
import com.talan.pfemanager.entity.Subject;

public class SubjectHelper {
	public static SubjectDTO subjectEntityToDto(Subject subject) {
		SubjectDTO subjectDTO = new SubjectDTO();
		subjectDTO.setId(subject.getId());
		subjectDTO.setTitle(subject.getTitle());
		subjectDTO.setDescription(subject.getDescription());
		subjectDTO.setPublicationDate(subject.getPublicationDate());
		subjectDTO.setStartDate(subject.getStartDate());
		subjectDTO.setEndDate(subject.getEndDate());
		subjectDTO.setTechnologies(subject.getTechnologies());		
		subjectDTO.setUserDTO(UserHelper.entityToDto(subject.getUser()));
		return subjectDTO;
	}
	public static List<SubjectDTO> subjectEntityToDto(List<Subject> subjects){
		return subjects.stream().map(x -> subjectEntityToDto(x)).collect(Collectors.toList());
	}
	public static Subject subjectDtoToEntity(SubjectDTO subjectDTO) {
		Subject subject = new Subject();
		subject.setId(subjectDTO.getId());
		subject.setTitle(subjectDTO.getTitle());
		subject.setDescription(subjectDTO.getDescription());
		subject.setPublicationDate(subjectDTO.getPublicationDate());
		subject.setStartDate(subjectDTO.getStartDate());
		subject.setEndDate(subjectDTO.getEndDate());
		subject.setTechnologies(subjectDTO.getTechnologies());	
		subject.setUser(UserHelper.dtoToEntity(subjectDTO.getUserDTO()));
		return subject;
	}
	public static List<Subject> subjectDtoToEntity(List<SubjectDTO> subjectDtos){
		return subjectDtos.stream().map(x -> subjectDtoToEntity(x)).collect(Collectors.toList());
	}

}
