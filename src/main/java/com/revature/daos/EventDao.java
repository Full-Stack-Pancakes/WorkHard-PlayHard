package com.revature.daos;

import java.sql.Date;
import java.util.List;

import com.revature.pojos.Event;

public interface EventDao {
	
	public List<Event> getEvents();
	public Event getEventById(int id);
	public void createEvent(Event event);
	public void updateEvent(Event event);
	public int deleteEventById(int id);
	public List<Integer> getEventIdByUserId(int id);
	public List<Event> getEventsByTimeFrame(Date startdate, Date duetime);
}
