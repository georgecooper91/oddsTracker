package com.trackodds.trackodds.resource;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.trackodds.trackodds.models.User;

public class AddUserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if(!user.getPassword().equals(user.getRepeatPassword())) {
			errors.rejectValue("repeatPassword", "PasswordsNoMatch");
		}
	}

}
