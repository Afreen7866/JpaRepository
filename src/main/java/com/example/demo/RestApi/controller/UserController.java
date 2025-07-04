package com.example.demo.RestApi.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.RestApi.dto.UserDto;

import com.example.demo.RestApi.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
		UserDto user1 = userService.createUser(user);
		return new ResponseEntity<>(user1,HttpStatus.CREATED);
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
		UserDto user2 = userService.getUserById(userId);
		return new ResponseEntity<>(user2,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> user = userService.getAllUsers();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid UserDto user){
		user.setId(userId);
		UserDto user1 = userService.updateUser(user);
		return new ResponseEntity<>(user1,HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
		userService.deleteUser(userId);
		return new ResponseEntity<>("User successfully deleted!",HttpStatus.OK);
	}
	
	
}
