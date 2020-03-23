package com.trackodds.trackodds.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackodds.trackodds.models.User;
import com.trackodds.trackodds.repository.AddUserRepository;
import com.trackodds.trackodds.repository.UserRepository;

@Service
public class AddUserServiceImp implements AddUserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddUserRepository addUserRepository;

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public boolean isUserAlreadyPresent(String userName) {
		Optional <User> registeredUser = userRepository.findByUserName(userName);
		
		return registeredUser.isPresent();
	}

}
