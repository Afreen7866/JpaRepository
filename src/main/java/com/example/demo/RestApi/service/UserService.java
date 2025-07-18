package com.example.demo.RestApi.service;

import java.util.List;

import com.example.demo.RestApi.dto.UserDto;
import com.example.demo.RestApi.model.User;

public interface UserService {

	UserDto createUser(UserDto userDto);
	
	UserDto getUserById(Long userId);
	
	List<UserDto> getAllUsers();
	
	UserDto updateUser(UserDto userDto); 
	
	void deleteUser(Long userId);
}
