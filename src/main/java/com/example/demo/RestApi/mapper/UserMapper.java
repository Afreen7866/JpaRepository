package com.example.demo.RestApi.mapper;

import com.example.demo.RestApi.dto.UserDto;
import com.example.demo.RestApi.model.User;

public class UserMapper {

	//convert user jpa entity into userdto
	public static UserDto mapToUserDto(User user) {
		UserDto userDto = new UserDto(
				user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail()
		);
		return userDto;
	}
	
	public static  User maptoUser(UserDto userDto) {
		User user = new User(
				userDto.getId(),
				userDto.getFirstName(),
				userDto.getLastName(),
				userDto.getEmail()
		);
		return user;
	}
}
