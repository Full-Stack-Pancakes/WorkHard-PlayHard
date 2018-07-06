package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.daos.EventDaoImpl;
import com.revature.exception.EventNotFoundException;
import com.revature.pojos.Event;

@CrossOrigin(origins="http://localhost:8000")
@RestController
@RequestMapping("/ue")
public class UEController {
	
	@Autowired
	EventDaoImpl edi;

	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Event> getEvents(){
		return edi.getEvents();
	}
	
	@GetMapping(value="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Event> getEventByUId(@PathVariable("id") int id){
		return edi.getEventsByUserId(id);
	}
}
