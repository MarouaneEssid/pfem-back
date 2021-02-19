package com.talan.pfemanager.dto;

import java.io.Serializable;

public class UserDTO implements  Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String email;
	private String password;
	private String firstname;
	private String lastname;
	private String school;
	private RoleDTO role;

	public UserDTO(String email, String password, String firstname, String lastname, String school, RoleDTO role) {
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.school = school;
		this.role = role;
	}

	public UserDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}
	
}
