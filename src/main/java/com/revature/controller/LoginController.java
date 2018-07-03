package com.revature.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.revature.daos.UserDaoImpl;
import com.revature.pojos.Event;
import com.revature.pojos.User;

@RestController
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
		return "loginTest";
	}
	@RequestMapping(value="login", method=RequestMethod.POST)
	//@PostMapping("WorkHard-PlayHard/login")
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
		req.setAttribute("userid", userid);
		return "redirect:/profile";
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	//@CrossOrigin(origins="http://localhost:4200/")
	public String profilePage() {
		return "test";
	}
	
//	@RequestMapping(value="signup", method=RequestMethod.GET)
//	public String signup() {
//		return "";
//	}
	@RequestMapping(value="signup", method=RequestMethod.POST)
	@PostMapping("WorkHard-PlayHard/signup")
	public String signupUser(HttpServletRequest req) {
		String username = req.getParameter("username");
		System.out.println(username);
		String password = req.getParameter("password");
		String cpassword = req.getParameter("confirmedpassword");
		String fname = req.getParameter("firstname");
		String lname = req.getParameter("lastname");
		//String bday = req.getParameter("birthday");
		//Date birthday = bday;
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		udi.createUser(new User(fname, lname, email, phone, password));
		return "redirect:/login";
	}
	
}
