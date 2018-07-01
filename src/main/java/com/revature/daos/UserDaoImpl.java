package com.revature.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.revature.pojos.User;
import com.revature.util.HibernateUtil;

@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public List<User> getUsers() {
		
		Session session = HibernateUtil.getSession();
		String hql = "from User";
		Query query = session.createQuery(hql);
		List<User> allUsers = query.list();
		return allUsers;
	}

	@Override
	public User getUserById(int id) {
		Session session = HibernateUtil.getSession();
		User user = (User) session.get(User.class, id);
		session.close();
		return user;
	}

	@Override
	public void createUser(User user) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
		session.close();
		
	}

	@Override
	public void updateUser(User user) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.update(user);
		transaction.commit();
		session.close();
		
	}

	@Override
	public int deleteUserById(int id) {
		Session session = HibernateUtil.getSession();
		String hql = "delete from User where id =:user_id";
		Query query = session.createQuery(hql);
		query.setParameter("user_id", id);
		int result = query.executeUpdate();
		return result;
	}
	
}
