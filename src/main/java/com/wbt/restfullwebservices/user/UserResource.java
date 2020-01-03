package com.wbt.restfullwebservices.user;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;

import javax.validation.Valid;

import  org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	UserDaoService userdaoservice;
	
	@GetMapping("/users")
	public List<User> findAll()
	{
		return userdaoservice.findAllUser();
	}
	
	@GetMapping("/users/{id}")
	public User findByid(@PathVariable int id)
	{
		User user= userdaoservice.findById(id);
		if(user==null)
			throw new UserNotFoundException("Id--"+id);
		
		//Resource<User> resource=new Resource<User>(user);
		
	//	ControllerLinkBuilder.linkTo(methodOn(this.getClass()));
		
		
		return user;
	}
	
	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id)
	{
		User user= userdaoservice.deleteById(id);
		if(user==null)
			throw new UserNotFoundException("Id--"+id);
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> created(@Valid @RequestBody User user)
	{
		User savedUser=userdaoservice.save(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}
