package com.revature.daos;

import java.util.List;

import com.revature.pojos.User;

public interface UserDao {
	
	public List<User> getUsers();
	public User getUserById(int id);
	public void createUser(String firstname, String lastname, String email, String phone);
	public void updateUser(int id, String firstname, String lastname, String email, String phone);
	public int deleteUserById(int id);

}
