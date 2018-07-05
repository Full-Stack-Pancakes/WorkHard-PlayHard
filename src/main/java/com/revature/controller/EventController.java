package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.daos.EventDaoImpl;
import com.revature.exception.EventNotFoundException;
import com.revature.exception.UserNotFoundException;
import com.revature.pojos.Event;
import com.revature.pojos.User;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/events")
public class EventController {
	
	@Autowired
	EventDaoImpl edi;

	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Event> getEvents(){
		return edi.getEvents();
	}
	
	@GetMapping(value="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Event getEventById(@PathVariable("id") int id) throws EventNotFoundException{
		if(edi.getEventById(id) != null) {
			return edi.getEventById(id);
		} else {
			throw new EventNotFoundException();
		}
		
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Event addEvent(@RequestBody Event event) {
		return edi.createEvent(event);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Event updateEvent(@RequestBody Event event){
		return edi.updateEvent(event);
	}
	
	@DeleteMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Event deleteEvent(@PathVariable("id") int id) throws EventNotFoundException {
		Event event = edi.getEventById(id);
		if(event != null) {
			edi.deleteEvent(event);
			return event;
		} else {
			throw new EventNotFoundException();
		}
	}
}
	
