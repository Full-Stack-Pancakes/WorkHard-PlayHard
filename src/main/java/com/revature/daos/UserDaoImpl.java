package com.revature.daos;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.pojos.User;
import com.revature.repositories.UserRepository;
import com.revature.util.HibernateUtil;

@Service
@Transactional
public class UserDaoImpl implements UserDao {
	
	@Autowired
	UserRepository urepo;

	@Override
	public List<User> getUsers() {
		return urepo.findAll();
	}

	@Override
	public User getUserById(int id) {
		return urepo.getOne(id);
	}

	@Override
	public User createUser(User user) {
		return urepo.save(user);
	}

	@Override
	public User updateUser(User user) {
		return urepo.save(user);
	}

	@Override
	public User deleteUser(User user) {
		urepo.delete(user);
		return user;
	}
	
}
