package com.talan.pfemanager.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.talan.pfemanager.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByEmail(String email);
    List<User> findUserByRoleId(int id);
    
    @Modifying
    @Transactional
    @Query(
			  value = "UPDATE user SET `password` = :password WHERE id = :id", 
			  nativeQuery = true)
    void updateUserPassword(@Param(value = "password") String password,@Param(value = "id") Integer  id);
    
    @Modifying
    @Transactional
    @Query(
			  value = "UPDATE user SET `firstname` = :firstname,`lastname` = :lastname,`role_id` = :role WHERE id = :id", 
			  nativeQuery = true)
    void updateUserInfo(@Param(value = "firstname") String firstname, @Param(value = "lastname") String lastname,@Param(value = "role") Integer  role, @Param(value = "id") Integer  id);
}
