package com.talan.pfemanager.helper;

import com.talan.pfemanager.dto.ApplicationDTO;
import com.talan.pfemanager.entity.Application;

public class ApplicationHelper {

	public ApplicationHelper() {
		super();
	}

	public static ApplicationDTO entityToDto(Application application) {
		ApplicationDTO applicationDTO = new ApplicationDTO();
		applicationDTO.setId(application.getId());
		applicationDTO.setCoverLetter(application.getCoverLetter());
		applicationDTO.setResume(application.getResume());
		applicationDTO.setSubjectDTO(SubjectHelper.subjectEntityToDto(application.getSubject()));
		applicationDTO.setUserDTO(UserHelper.entityToDto(application.getUser()));
		return applicationDTO;
	}

	public static Application dtoToEntity(ApplicationDTO applicationDTO) {
		Application application = new Application();
		application.setId(applicationDTO.getId());
		application.setResume(applicationDTO.getResume());
		application.setSubject(SubjectHelper.subjectDtoToEntity(applicationDTO.getSubjectDTO()));
		application.setUser(UserHelper.dtoToEntity(applicationDTO.getUserDTO()));
		application.setCoverLetter(applicationDTO.getCoverLetter());
		return application;
	}

}
