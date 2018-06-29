package com.revature.daos;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.revature.pojos.Event;
import com.revature.pojos.User;
import com.revature.util.HibernateUtil;

@Repository
public class EventDaoImpl implements EventDao {

	@Override
	public List<Event> getEvents() {
		Session session = HibernateUtil.getSession();
		String hql = "from Event";
		Query query = session.createQuery(hql);
		List<Event> allEvents = query.list();
		return allEvents;
	}

	@Override
	public Event getEventById(int id) {
		Session session = HibernateUtil.getSession();
		Event event = (Event) session.get(Event.class, id);
		session.close();
		return event;
	}


	@Override
	public void createEvent(Event event) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(event);
		transaction.commit();
		session.close();
		
	}

	@Override
	public void updateEvent(Event event) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.update(event);
		transaction.commit();
		session.close();
		
	}

	@Override
	public int deleteEventById(int id) {
		Session session = HibernateUtil.getSession();
		String hql = "delete from Event where id =:event_id";
		Query query = session.createQuery(hql);
		query.setParameter("event_id", id);
		int result = query.executeUpdate();
		return result;
	}
	
	

}
