package com.ulissessantana.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ulissessantana.workshopmongo.domain.Post;
import com.ulissessantana.workshopmongo.domain.User;
import com.ulissessantana.workshopmongo.dto.AuthorDTO;
import com.ulissessantana.workshopmongo.dto.CommentDTO;
import com.ulissessantana.workshopmongo.repository.PostRepository;
import com.ulissessantana.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation<UserReposiroty> implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("11/03/2024"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO( maria));
		Post post2 = new Post(null, sdf.parse("10/03/2024"), "Bom dia", "Acordei agora",new AuthorDTO( maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("12/03/2024"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("12/03/2024"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("12/03/2024"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		System.out.println("Posts associados ao usuário: " + maria.getPosts());
		userRepository.save(maria);
	}

}
