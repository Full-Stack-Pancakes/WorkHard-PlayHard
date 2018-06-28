package com.revature.daos;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.revature.pojos.Event;

public interface EventDao {
	
	public List<Event> getEvents();
	public Event getEventById(int id);
	public List<Event> getEventsByUserId(int id);
	public void createEvent(int priority, Time eventLength, Time startTime, String eventType, boolean splitable, Time minLength, Time inputTime, String dayOfTheWeek, Date startDate);
	public void updateEvent(int id, Time eventLength, Time startTime, String eventType, boolean splitable, Time minLength, Time inputTime, String dayOfTheWeek, Date startDate);
	public int deleteEventById(int id);
	

}
