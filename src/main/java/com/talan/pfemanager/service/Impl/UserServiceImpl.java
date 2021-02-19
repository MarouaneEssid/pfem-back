package com.talan.pfemanager.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talan.pfemanager.dto.UserDTO;
import com.talan.pfemanager.entity.User;
import com.talan.pfemanager.helper.UserHelper;
import com.talan.pfemanager.repository.UserRepository;
import com.talan.pfemanager.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDTO register(UserDTO userDTO) {
		User user = UserHelper.dtoToEntity(userDTO);
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		return UserHelper.entityToDto(userRepository.save(user));
	}

	@Override
	public List<UserDTO> getUsers() {
		List<UserDTO> allUsersDTO = new ArrayList<UserDTO>();
		userRepository.findAll().forEach((user) -> allUsersDTO.add(UserHelper.entityToDto(user)));
		return allUsersDTO;
	}

	@Override
	public UserDTO getUser(int id) throws NullPointerException {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return UserHelper.entityToDto(user.get());
		}
		throw new NullPointerException("User not found");
	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();
	}

	@Override
	public UserDTO updateUser(int id, UserDTO updatedUser) throws NullPointerException {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			if (updatedUser.getPassword() != null && updatedUser.getPassword().length() > 0) {
				updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
			} else {
				updatedUser.setPassword(user.get().getPassword()); // password field empty: give user old password
			}
			return UserHelper.entityToDto(userRepository.save(UserHelper.dtoToEntity(updatedUser)));
		}
		throw new NullPointerException("could not find user id = " + id);
	}

	@Override
	public List<UserDTO> FindUsersByRole(int id) {
		List<UserDTO> allUsersDTOByRole = new ArrayList<UserDTO>();
		userRepository.findUserByRoleId(id).forEach((user) -> allUsersDTOByRole.add(UserHelper.entityToDto(user)));
		return allUsersDTOByRole;
	}

	@Override
	public void updateUserPassword(String password, int id) {
		password = passwordEncoder.encode(password);
		userRepository.updateUserPassword(password, id);
	}
}
