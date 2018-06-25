package com.revature.util;

import org.hibernate.Session;

import com.revature.pojos.Event;
import com.revature.pojos.User;

public class Driver {

	public static void main(String[] args) {
		Session s = HibernateUtil.getSession();
		s.close();
		User user = new User();
		Event event = new Event();
	}

}
