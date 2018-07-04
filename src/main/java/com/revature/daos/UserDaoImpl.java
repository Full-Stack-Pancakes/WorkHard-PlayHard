package com.revature.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.revature.pojos.User;
import com.revature.util.HibernateUtil;


@Repository
public class UserDaoImpl implements UserDao {
	
	@Override
	public List<User> getUsers() {
		Session s = HibernateUtil.getSession();
		List<User> ulist = s.createQuery("from User").list();
		s.close();
		return ulist;
	}

	@Override
	public User getUserById(int id) {
		Session s = HibernateUtil.getSession();
		User u = (User) s.get(User.class, id);
		s.close();
		return u;
	}

	@Override
	public User createUser(User user) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.save(user);
		tx.commit();
		s.close();
		return user;
	}

	@Override
	public User updateUser(User user) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.update(user);
		transaction.commit();
		session.close();
		return user;
	}

	@Override
	public User deleteUser(User user) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(user);
		transaction.commit();
		session.close();
		return user;
	}
	
}
