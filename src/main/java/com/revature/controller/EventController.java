package com.revature.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.daos.EventDaoImpl;
import com.revature.pojos.Event;

public class EventController {
	
	@Autowired
	EventDaoImpl edi;

	@GetMapping("/events")
	@ResponseBody
	public List<Event> getEvents(){
		return null;
	}
	
	@RequestMapping(value="addevent", method=RequestMethod.POST)
	@PostMapping("WorkHard-PlayHard/addevent")
	public String addEvent(HttpServletRequest req) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss");
		int priority = Integer.parseInt(req.getParameter("priority"));
		String eventlength = req.getParameter("eventlength");
		long mslength1 = sdf.parse(eventlength).getTime();
		Time elength = new Time(mslength1);
		String starttime = req.getParameter("starttime");
		java.util.Date startdate = sdf.parse(starttime);
		java.sql.Date sqlStartDate = new java.sql.Date(startdate.getTime());  
		String duetime = req.getParameter("duetime");
		java.util.Date duedate = sdf.parse(duetime);
		java.sql.Date sqlDueDate = new java.sql.Date(duedate.getTime());
		String eventtype = req.getParameter("eventtype");
		Boolean splitable = Boolean.parseBoolean(req.getParameter("splitable"));
		String minlength = req.getParameter("minlength");
		long mslength2 = sdf.parse(eventlength).getTime();
		Time mlength = new Time(mslength2);
		long millis = System.currentTimeMillis();
		Date inputtime = new java.sql.Date(millis);
		String dayofweek = req.getParameter("dayofweek");
		String location = req.getParameter("location");
		String description = req.getParameter("description");
		String timezone = req.getParameter("timezone");
		//addEvent(new Event(priority, elength, sqlStartDate, sqlDueDate, eventtype, splitable, mlength, inputtime, dayofweek, location, description, timezone));
		edi.createEvent(new Event(priority, elength, sqlStartDate, sqlDueDate, eventtype, splitable, mlength, inputtime, dayofweek, location, description, timezone));
		return "redirect:/profile";
	}
	
	@RequestMapping(value="addevent", method=RequestMethod.POST)
	@PostMapping("WorkHard-PlayHard/addevent")
	public String deleteEvent(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("id"));
		edi.deleteEventById(id);
		return "redirect:/profile";
	}
	
	@RequestMapping(value="updateevent", method=RequestMethod.POST)
	@PostMapping("WorkHard-PlayHard/updateevent")
	public String updateEvent(HttpServletRequest req) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss");
		int priority = Integer.parseInt(req.getParameter("priority"));
		String eventlength = req.getParameter("eventlength");
		long mslength1 = sdf.parse(eventlength).getTime();
		Time elength = new Time(mslength1);
		String starttime = req.getParameter("starttime");
		java.util.Date startdate = sdf.parse(starttime);
		java.sql.Date sqlStartDate = new java.sql.Date(startdate.getTime());  
		String duetime = req.getParameter("duetime");
		java.util.Date duedate = sdf.parse(duetime);
		java.sql.Date sqlDueDate = new java.sql.Date(duedate.getTime());
		String eventtype = req.getParameter("eventtype");
		Boolean splitable = Boolean.parseBoolean(req.getParameter("splitable"));
		String minlength = req.getParameter("minlength");
		long mslength2 = sdf.parse(eventlength).getTime();
		Time mlength = new Time(mslength2);
		long millis = System.currentTimeMillis();
		Date inputtime = new java.sql.Date(millis);
		String dayofweek = req.getParameter("dayofweek");
		String location = req.getParameter("location");
		String description = req.getParameter("description");
		String timezone = req.getParameter("timezone");
		//addEvent(new Event(priority, elength, sqlStartDate, sqlDueDate, eventtype, splitable, mlength, inputtime, dayofweek, location, description, timezone));
		int id = Integer.parseInt(req.getParameter("id"));
		edi.deleteEventById(id);
		edi.createEvent(new Event(priority, elength, sqlStartDate, sqlDueDate, eventtype, splitable, mlength, inputtime, dayofweek, location, description, timezone));
		return "redirect:/profile";
	}
}
