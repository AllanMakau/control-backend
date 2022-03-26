package br.com.mksoftware.control.parse;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mksoftware.control.dtos.response.UserResponse;
import br.com.mksoftware.control.dtos.resquest.UserRequest;
import br.com.mksoftware.control.entities.User;


@Component
public class UserParse {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public User toDomainObject(UserRequest userRequest) {
		return modelMapper.map(userRequest, User.class);
	}

	public UserResponse toModelResponse(User user) {
		return modelMapper.map(user, UserResponse.class);
	}
	
	public List<UserResponse> toCollectionModel(List<User> users) {
		return users.stream()
				.map(user -> toModelResponse(user))
				.collect(Collectors.toList());  
	}

}
