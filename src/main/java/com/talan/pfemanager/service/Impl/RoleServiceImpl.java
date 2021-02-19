package com.talan.pfemanager.service.Impl;

import com.talan.pfemanager.dto.RoleDTO;
import com.talan.pfemanager.entity.Role;
import com.talan.pfemanager.helper.RoleHelper;
import com.talan.pfemanager.repository.RoleRepository;
import com.talan.pfemanager.service.RoleService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepo;

	@Override
	public RoleDTO createRole(RoleDTO roleDto) {
		Role role = RoleHelper.dtoToEntity(roleDto);
		Role createdRole = roleRepo.save(role);
		return RoleHelper.entityToDto(createdRole);
	}

	@Override
	public RoleDTO getRole(int id) throws NullPointerException {
		Optional<Role> role = roleRepo.findById(id);
		if (role.isPresent()) {
			return RoleHelper.entityToDto(role.get());
		}
		throw new NullPointerException("Role not found");
	}

	@Override
	public List<RoleDTO> getRoles() {
		List<Role> roles = roleRepo.findAll();
		return RoleHelper.entityToDto(roles);
	}

	@Override
	public boolean updateRole(int id, RoleDTO roleDto) {
		Optional<Role> role = roleRepo.findById(id);
		if (role.isPresent()) {
			roleRepo.save(role.get());
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteRole(int id) {
		Optional<Role> role = roleRepo.findById(id);
		if (role.isPresent()) {
			roleRepo.deleteById(id);
			return true;
		}
		return false;
	}
}
