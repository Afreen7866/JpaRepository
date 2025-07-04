package com.example.demo.RestApi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.RestApi.dto.UserDto;
import com.example.demo.RestApi.exception.EmailAlreadyExistsException;
import com.example.demo.RestApi.exception.ResourceNotFoundException;
import com.example.demo.RestApi.mapper.UserMapper;
import com.example.demo.RestApi.model.User;
import com.example.demo.RestApi.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		//convert userdto into user jpa entity
		/*User user = new User(
				userDto.getId(),
				userDto.getFirstName(),
				userDto.getLastName(),
				userDto.getEmail()
				);
	     User user1 = userRepository.save(user);
	     
	     UserDto user2 = new UserDto(
	    		 user1.getId(),
	    		 user1.getFirstName(),
	    		 user1.getLastName(),
	    		 user1.getEmail()
	    		 );
		return user2;*/
	     
		//User user = UserMapper.maptoUser(userDto);
		Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
		if(optionalUser.isPresent()) {
			throw new EmailAlreadyExistsException("Email already exists for user");
		}
		User user = modelMapper.map(userDto, User.class);
		User user1 = userRepository.save(user);
		//UserDto user2 = UserMapper.mapToUserDto(user1);
		UserDto user2 = modelMapper.map(user1, UserDto.class);
		return user2;
	     
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user1 = userRepository.findById(userId).orElseThrow(
				 () -> new ResourceNotFoundException("User", "id", userId)
				);
		//User user = user1.get();
		//return UserMapper.mapToUserDto(user);
		return modelMapper.map(user1, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> user = userRepository.findAll();
		//return user.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
		return user.stream().map(users -> modelMapper.map(users, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		User user1 = userRepository.findById(userDto.getId()).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", userDto.getId())
				);
		user1.setFirstName(userDto.getFirstName());
		user1.setLastName(userDto.getLastName());
		user1.setEmail(userDto.getEmail());
		User user2 = userRepository.save(user1);
		//return UserMapper.mapToUserDto(user2);
		return modelMapper.map(user2, UserDto.class);
	}

	@Override
	public void deleteUser(Long userId) {
		User user1 = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", userId)
				);
		userRepository.deleteById(userId);
	}

}
