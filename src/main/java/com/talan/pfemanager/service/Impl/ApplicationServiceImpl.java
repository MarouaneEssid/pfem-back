package com.talan.pfemanager.service.Impl;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.talan.pfemanager.dto.ApplicationDTO;
import com.talan.pfemanager.entity.Application;
import com.talan.pfemanager.helper.ApplicationHelper;
import com.talan.pfemanager.repository.ApplicationRepository;
import com.talan.pfemanager.service.ApplicationService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ApplicationServiceImpl implements ApplicationService {
	private final Path root = Paths.get("uploads");
	private String pathResume;

	@Autowired
	ApplicationRepository applicationRepository;

	@Override
	public void addApplication(ApplicationDTO applicationDTO) {
		applicationDTO.setResume(this.pathResume);
		Application application = ApplicationHelper.dtoToEntity(applicationDTO);
		applicationRepository.save(application);
	}

	@Override
	public void deleteApplicationById(int id) {
		applicationRepository.deleteById(id);

	}

	@Override
	public void deleteAllApplication() {
		applicationRepository.deleteAll();

	}

	@Override
	public List<ApplicationDTO> getAllapplications() {
		List<ApplicationDTO> allApplicationsDTO = new ArrayList<ApplicationDTO>();
		applicationRepository.findAll()
				.forEach((application) -> allApplicationsDTO.add(ApplicationHelper.entityToDto(application)));
		return allApplicationsDTO;
	}

	@Override
	public List<ApplicationDTO> getAppliationsByUserId(int id) {
		List<ApplicationDTO> allApplicationsDTO = new ArrayList<ApplicationDTO>();
		applicationRepository.findApplicationByUserId(id)
				.forEach((application) -> allApplicationsDTO.add(ApplicationHelper.entityToDto(application)));
		return allApplicationsDTO;
	}

	@Override
	public ApplicationDTO getApplicationById(int id) throws NullPointerException {
		Optional<Application> application = applicationRepository.findById(id);
		if (application.isPresent()) {
			ApplicationDTO applicationDTO = ApplicationHelper.entityToDto(application.get());
			return applicationDTO;
		}
		throw new NullPointerException("Application not found");

	}

	@Override
	public void saveResume(MultipartFile file) {
		LocalDateTime now = LocalDateTime.now();
		try {
			String fileName=file.getOriginalFilename();
			String newName = (now + fileName).replaceAll(":", "-");
			this.pathResume = this.root.resolve(newName).toString();
			Files.copy(file.getInputStream(), this.root.resolve(newName));
		} catch (NullPointerException e) {
			throw new NullPointerException("file not found " + e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}

	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}

	}

	@Override
	public List<ApplicationDTO> findByCollab(Integer id) {
		List<ApplicationDTO> allApplicationsBycollab = new ArrayList<ApplicationDTO>();
		applicationRepository.findByCollab(id)
				.forEach((application) -> allApplicationsBycollab.add(ApplicationHelper.entityToDto(application)));
		return allApplicationsBycollab;
	}
}
