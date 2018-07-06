package com.revature.daos;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.revature.pojos.Event;
import com.revature.pojos.User;
import com.revature.pojos.UserEvent;
import com.revature.util.HibernateUtil;

@Repository
public class EventDaoImpl implements EventDao {

	@Override
	public List<Event> getEvents() {
		Session s = HibernateUtil.getSession();
		List<Event> elist = s.createQuery("from Event").list();
		s.close();
		return elist;
	}

	@Override
	public Event getEventById(int id) {
		Session session = HibernateUtil.getSession();
		Event event = (Event) session.get(Event.class, id);
		session.close();
		return event;
	}


	@Override
	public Event createEvent(Event event) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(event);
		transaction.commit();
		session.close();
		return event;
	}

	@Override
	public Event updateEvent(Event event) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.update(event);
		transaction.commit();
		session.close();
		return event;
	}

	@Override
	public Event deleteEvent(Event event) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(event);
		transaction.commit();
		session.close();
		return event;
	}
	
	@Override
	public List<Event> getEventsByTimeFrame(Date sdate, Date dtime) {
		List<Event> eventlist = new ArrayList<Event>();
		Session session = HibernateUtil.getSession();
		String hql = "from Event where starttime between :sdate and :dtime";
		Query query = session.createQuery(hql);
		query.setParameter("sdate", sdate);
		query.setParameter("dtime", dtime);
		eventlist = query.list();
		return eventlist;
	}

	@Override
	public List<Event> getEventsByUserId(int id) {
		List<Event> eventlist = new ArrayList<Event>();
		Session session = HibernateUtil.getSession();
		String hql = "from Event where userid = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		eventlist = query.list();
		return eventlist;
	}
}
