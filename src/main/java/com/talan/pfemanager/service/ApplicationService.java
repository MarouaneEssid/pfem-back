package com.talan.pfemanager.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.talan.pfemanager.dto.ApplicationDTO;

public interface ApplicationService {

	void addApplication(ApplicationDTO applicationDTO);

	void deleteApplicationById(int id);

	void deleteAllApplication();

	List<ApplicationDTO> getAllapplications();

	List<ApplicationDTO> getAppliationsByUserId(int id);

	ApplicationDTO getApplicationById(int id);

	void saveResume(MultipartFile file);

	Resource load(String filename);

	List<ApplicationDTO> findByCollab(Integer id);

}
