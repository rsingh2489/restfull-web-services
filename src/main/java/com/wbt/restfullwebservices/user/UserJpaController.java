package com.wbt.restfullwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJpaController {

	//@Autowired
	//UserDaoService userdaoservice;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> findAll()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public Optional<User> findByid(@PathVariable int id)
	{
		Optional<User> user= userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("Id--"+id);
		return user;
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		userRepository.deleteById(id);
		//if(user==null)
		//	throw new UserNotFoundException("Id--"+id);
	//	return user;
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> created(@Valid @RequestBody User user)
	{
		User savedUser=userRepository.save(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> findPostByid(@PathVariable int id)
	{
		Optional<User> user= userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("Id--"+id);
		return user.get().getPost();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> created(@PathVariable int id,@Valid @RequestBody Post post)
	{
		
		Optional<User> userOptional= userRepository.findById(id);
		if(!userOptional.isPresent())
			throw new UserNotFoundException("Id--"+id);
		
		User user=userOptional.get();
		post.setUser(user);
		
		System.out.println(post);
		postRepository.save(post);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}
