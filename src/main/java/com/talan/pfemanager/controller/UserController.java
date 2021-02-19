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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.talan.pfemanager.dto.UserDTO;
import com.talan.pfemanager.service.UserService;

@RestController
public class UserController {

    @Autowired
    private  UserService userService;
        
    @PostMapping("/register")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }
    
    @GetMapping("/users/all")
    @ResponseBody
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
    	userService.deleteUser(id);
    }
    
    @DeleteMapping("/users")
    public void deleteAllUsers() {
    	userService.deleteAllUsers();
    }

    @PutMapping("/users/{id}")
    public UserDTO updateUser(@PathVariable int id, @RequestBody UserDTO updatedUser) throws Exception {
    	return userService.updateUser(id, updatedUser);
    }
    
    @GetMapping("/users/role/{id}")
    public List<UserDTO> getUsersByRole (@PathVariable int id) {
        return userService.FindUsersByRole(id);
    }
    
    @PutMapping("/users/password/{id}")
    public String updateUserPassword(@PathVariable int id, @RequestBody String password) throws Exception {
    	userService.updateUserPassword(password, id);
    	return "{\"success\": \"updated\"}";
    }


}
