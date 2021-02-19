package com.talan.pfemanager.helper;

import com.talan.pfemanager.dto.UserDTO;
import com.talan.pfemanager.entity.User;

public class UserHelper {

	public static UserDTO entityToDto(User user) {

		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(user.getEmail());
		userDTO.setFirstname(user.getFirstname());
		userDTO.setId(user.getId());
		userDTO.setLastname(user.getLastname());
		userDTO.setPassword(user.getPassword());
		userDTO.setSchool(user.getSchool());
		userDTO.setRole(RoleHelper.entityToDto(user.getRole()));
		return userDTO;
	}

	public static User dtoToEntity(UserDTO userDTO) {
		
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setFirstname(userDTO.getFirstname());
		user.setId(userDTO.getId());
		user.setLastname(userDTO.getLastname());
		user.setPassword(userDTO.getPassword());
		user.setSchool(userDTO.getSchool());
		user.setRole(RoleHelper.dtoToEntity(userDTO.getRole()));
		return user;
	}

}
