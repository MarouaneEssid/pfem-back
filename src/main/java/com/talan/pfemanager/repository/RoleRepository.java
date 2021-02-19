package com.talan.pfemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talan.pfemanager.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByName(String name);
}
