package com.talan.pfemanager.helper;

import com.talan.pfemanager.dto.RoleDTO;
import com.talan.pfemanager.entity.Role;
import java.util.List;
import java.util.stream.Collectors;

public class RoleHelper {
    public static RoleDTO entityToDto(Role role) {
        RoleDTO roleDto = new RoleDTO();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }
    
    public static Role dtoToEntity(RoleDTO roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }
    
    public static List<RoleDTO> entityToDto(List<Role> roles) {
        return roles.stream().map(role -> entityToDto(role)).collect(Collectors.toList());
    }
    
    public static  List<Role> dtoToEntity(List<RoleDTO> roleDtos) {
        return roleDtos.stream().map(roleDto -> dtoToEntity(roleDto)).collect(Collectors.toList());
    }
}
