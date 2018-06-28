package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.daos.UserDaoImpl;
import com.revature.exception.UserNotFoundException;
import com.revature.pojos.User;

@Controller
public class UserController {
	List<User> ulist = new ArrayList<User>();
	
	@Autowired
	UserDaoImpl udi;
	
	@GetMapping("/users")
	@ResponseBody
	public List<User> getUsers(){
		ulist = udi.getUsers();
		return ulist;
	}
	
	@GetMapping("/users/{id}")
	@ResponseBody
	public User getUserByPathId(@PathVariable("id") int id) {
		User u = udi.getUserById(id);
		if(u == null) {
			throw new UserNotFoundException();
		}
		return u;
	}
}
