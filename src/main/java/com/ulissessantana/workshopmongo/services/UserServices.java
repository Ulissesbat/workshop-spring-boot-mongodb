package com.ulissessantana.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ulissessantana.workshopmongo.domain.User;
import com.ulissessantana.workshopmongo.repository.UserRepository;
import com.ulissessantana.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserServices {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();		
	}
	
	public User findById(String id) {
	    Optional<User> userOptional = repo.findById(id);
	    
	    if (userOptional.isEmpty()) {
	        throw new ObjectNotFoundException("Objeto não encontrado");
	    }
	    
	    return userOptional.get();
	}

}
