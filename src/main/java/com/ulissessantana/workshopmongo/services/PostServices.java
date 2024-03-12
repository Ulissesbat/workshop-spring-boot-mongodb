package com.ulissessantana.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ulissessantana.workshopmongo.domain.Post;
import com.ulissessantana.workshopmongo.repository.PostRepository;
import com.ulissessantana.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostServices {

	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
	    Optional<Post> post = repo.findById(id);

	    if (post.isEmpty()) {
	        throw new ObjectNotFoundException("Objeto não encontrado");
	    }

	    return post.get();
	}
}