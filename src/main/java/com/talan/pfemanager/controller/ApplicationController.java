package com.talan.pfemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.talan.pfemanager.dto.ApplicationDTO;
import com.talan.pfemanager.message.ResponseMessage;
import com.talan.pfemanager.service.ApplicationService;

@RestController
@RequestMapping(value = "/applications")
public class ApplicationController {
	@Autowired
	ApplicationService applicationService;

	@PostMapping("")
	public void createApplication(@RequestBody ApplicationDTO applicationDTO) {
		applicationService.addApplication(applicationDTO);

	}

	@DeleteMapping("/{id}")
	public void deleteApplicationById(@PathVariable int id) {
		applicationService.deleteApplicationById(id);
	}

	@GetMapping("/all")
	@ResponseBody
	public List<ApplicationDTO> getAllApplications() {
		return applicationService.getAllapplications();
	}

	@DeleteMapping("")
	public void deleteAllApplications() {
		applicationService.deleteAllApplication();
	}

	@GetMapping("/user/{id}")
	@ResponseBody
	public List<ApplicationDTO> getApplicationsByUserId(@PathVariable int id) {
		return applicationService.getAppliationsByUserId(id);
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ApplicationDTO getApplicationById(@PathVariable int id) {
		return applicationService.getApplicationById(id);
	}

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";

		try {
			applicationService.saveResume(file);
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("download/{id}/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = applicationService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("/collab/{id}")
	@ResponseBody
	public List<ApplicationDTO> getApplicationsByCollab(@PathVariable int id) {
		return applicationService.findByCollab(id);
	}

}
