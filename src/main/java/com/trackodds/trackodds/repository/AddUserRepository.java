package com.trackodds.trackodds.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trackodds.trackodds.models.User;

@Repository
public interface AddUserRepository extends JpaRepository<User, Integer> {
	
	Optional <User> findByUserName(String userName);
	
	

}
