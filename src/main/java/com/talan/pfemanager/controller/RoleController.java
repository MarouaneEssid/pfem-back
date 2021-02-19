package com.talan.pfemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.talan.pfemanager.dto.RoleDTO;
import com.talan.pfemanager.service.RoleService;

@RestController
@ResponseBody
@RequestMapping(value="/roles")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public List<RoleDTO> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping("/{id}")
    public RoleDTO getRole(@PathVariable int id) {
        return roleService.getRole(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDTO createRole(@RequestBody RoleDTO roleDto) {
        return roleService.createRole(roleDto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteRole(@PathVariable int id) {
        return roleService.deleteRole(id);
    }

    @PutMapping("/{id}")
    public boolean updateRole(@PathVariable int id, @RequestBody RoleDTO roleDto){
        return roleService.updateRole(id, roleDto);
    }    

}
