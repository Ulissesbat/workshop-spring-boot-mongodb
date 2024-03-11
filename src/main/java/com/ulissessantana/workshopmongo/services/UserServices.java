package com.ulissessantana.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ulissessantana.workshopmongo.domain.User;
import com.ulissessantana.workshopmongo.repository.UserRespository;

@Service
public class UserServices {

	@Autowired
	private UserRespository repo;
	
	public List<User> findAll(){
		return repo.findAll();		
	}
}