package com.talan.pfemanager.dto;

import java.io.Serializable;

public class ApplicationDTO implements  Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String coverLetter;
	private String resume;
	private UserDTO userDTO;
	private SubjectDTO subjectDTO;

	public ApplicationDTO() {
		super();
	}

	public ApplicationDTO(String coverLetter, String resume, UserDTO userDTO, SubjectDTO subjectDTO) {
		super();
		this.coverLetter = coverLetter;
		this.resume = resume;
		this.userDTO = userDTO;
		this.subjectDTO = subjectDTO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public SubjectDTO getSubjectDTO() {
		return subjectDTO;
	}

	public void setSubjectDTO(SubjectDTO subjectDTO) {
		this.subjectDTO = subjectDTO;
	}

}
