package com.revature.daos;

import java.sql.Date;
import java.util.List;

import com.revature.pojos.Event;

public interface EventDao {
	
	public List<Event> getEvents();
	public Event getEventById(int id);
	public Event createEvent(Event event);
	public Event updateEvent(Event event);
	public Event deleteEvent(Event event);
	public List<Event> getEventsByTimeFrame(Date startdate, Date duetime);
}
