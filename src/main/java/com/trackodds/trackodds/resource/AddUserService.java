package com.trackodds.trackodds.resource;

import com.trackodds.trackodds.models.User;

public interface AddUserService {

	public void saveUser(User user);
	
	public boolean isUserAlreadyPresent(String userName);
}
