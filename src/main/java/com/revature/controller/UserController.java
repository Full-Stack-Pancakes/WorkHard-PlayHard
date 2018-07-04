package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.daos.UserDaoImpl;
import com.revature.exception.UserNotFoundException;
import com.revature.pojos.User;

@CrossOrigin(origins="http://localhost:4200")
@RestController // takes the place of @Controller and @ResponseBody on each of the methods
@RequestMapping("/users") // maps every request in this class
public class UserController {
	
	@Autowired
	UserDaoImpl udi;
	
	//@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsers(){
		List<User> ulist = udi.getUsers();
		return ulist;
	}
	
	//@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public User getUserByPathId(@PathVariable("id") int id)  throws UserNotFoundException{
		User u = udi.getUserById(id);
		if(u != null) {
			return udi.getUserById(id);
		} else {
			throw new UserNotFoundException();
		}
		
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public User createUser(@RequestBody User user) {
		return udi.createUser(user);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@RequestBody User user) {
		return udi.updateUser(user);
	}
	
	@DeleteMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public User deleteUser(@RequestBody User user) {
		udi.deleteUser(user);
		return user;
	}
}
