package com.revature.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.pojos.Event;

public class EventController {

	@GetMapping("/events")
	@ResponseBody
	public List<Event> getEvents(){
		return null;
	}
}
