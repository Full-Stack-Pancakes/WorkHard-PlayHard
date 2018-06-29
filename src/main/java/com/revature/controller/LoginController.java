package com.revature.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.revature.daos.UserDaoImpl;
import com.revature.pojos.Event;
import com.revature.pojos.User;

@Controller
public class LoginController {
	
	
	@Autowired
	UserDaoImpl udi;
	
	@GetMapping("/users")
	@ResponseBody
	public List<User> getUsers(){
		List<User> ulist = new ArrayList<User>();
		ulist = udi.getUsers();
		return ulist;
	}
	
	@RequestMapping(value ="/login", method=RequestMethod.GET)
	public String Login() {
		return "index";
	}
	@PostMapping("/login")
	public String LoginUser(HttpServletRequest req) {
		String username = req.getParameter("username");
		String password = req.getParameter("pwd");
		List<User> ulist = udi.getUsers();
		int userid = 0;
		for(User u : ulist) {
			if(u.getEmail().equals(username) && u.getPassword().equals(password)) {
				userid = u.getUserid();
			}
		}
		//set the sessions
		return "redirect:/profile";
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String profilePage() {
		return "test";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup() {
		return "index";
	}
	@PostMapping("/signup")
	public String signupUser(HttpServletRequest req) {
		String username = req.getParameter("username");
		String password = req.getParameter("username");
		String cpassword = req.getParameter("username");
		String fname = req.getParameter("username");
		String lname = req.getParameter("username");
		String bday = req.getParameter("username");
		//Date birthday = bday;
		String email = req.getParameter("username");
		String phone = req.getParameter("username");
		udi.createUser(new User(fname, lname, email, phone));
		return "redirect:/login";
	}
}
