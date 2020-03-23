package com.trackodds.trackodds.resource;

import com.trackodds.trackodds.models.MyUserDetails;
import com.trackodds.trackodds.models.User;
import com.trackodds.trackodds.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	
	@Autowired
    UserRepository userRepository;
	
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	Optional<User> user = userRepository.findByUserName(userName);

    	user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return user.map(MyUserDetails::new).get();
    }
    
}
