package com.revature.daos;

import java.util.List;

import com.revature.pojos.User;

public interface UserDao {
	
	public List<User> getUsers();
	public User getUserById(int id);
	public void createUser(User user);
	public void updateUser(User user);
	public int deleteUserById(int id);

}
