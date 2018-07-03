package com.revature.daos;

import java.util.List;

import com.revature.pojos.User;

public interface UserDao {
	
	public List<User> getUsers();
	public User getUserById(int id);
	public User createUser(User user);
	public User updateUser(User user);
	public User deleteUser(User user);
}
