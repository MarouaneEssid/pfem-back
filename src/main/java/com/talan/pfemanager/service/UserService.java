package com.talan.pfemanager.service;

import java.util.List;

import com.talan.pfemanager.dto.UserDTO;

public interface UserService {

	UserDTO register(UserDTO user);

	List<UserDTO> getUsers();

	UserDTO getUser(int id);
        
	void deleteUser(int id);
	
	void deleteAllUsers();

	UserDTO updateUser(int id, UserDTO updatedUser) throws Exception;
	
	List<UserDTO> FindUsersByRole(int id);
        
        void updateUserPassword (String password, int id);
	
}
