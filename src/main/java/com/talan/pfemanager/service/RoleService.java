package com.talan.pfemanager.service;

import java.util.List;

import com.talan.pfemanager.dto.RoleDTO;


public interface RoleService {

    RoleDTO createRole(RoleDTO roleDto);

    RoleDTO getRole(int id);

    List<RoleDTO> getRoles();

    boolean updateRole(int id, RoleDTO roleDto);

    boolean deleteRole(int id);
}
