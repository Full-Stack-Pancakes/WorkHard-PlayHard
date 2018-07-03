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
		String hql = "delete from Event where EVENTID =:event_id";
		Query query = session.createQuery(hql);
		query.setParameter("event_id", id);
		int result = query.executeUpdate();
		return result;
	}
	
	@Override
	public List<Integer> getEventIdByUserId(int id){
		List<Integer> idlist = new ArrayList<Integer>();
		Session session = HibernateUtil.getSession();
		String hql = "select event_id from UserEvent where USERID =:user_id";
		Query query = session.createQuery(hql);
		query.setParameter("user_id", id);
		idlist = query.list();
		return idlist;
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

	
}
