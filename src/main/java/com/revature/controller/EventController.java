package com.revature.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
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
import com.revature.pojos.Event;
import com.revature.pojos.EventCompare;
import com.revature.pojos.EventCompare2;
import com.revature.pojos.EventTimeCompare;
import com.revature.pojos.User;

@CrossOrigin(origins="http://localhost:8000")
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
	public Event addEvents(@RequestBody Event event) {
		Event e = addEvent(event);
		if(e != null) {
			return e;
		}
		return event;
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
//	long st;
//	long et;
	public Event addEvent(Event e) {
		List<Event> thelist = null;
		List<Event> templist = null;
		List<Event> templist2 = null;
		//static part
		if (e.getEventtype().equals("static")) {
			List<Event> elist = edi.getEventsByTimeFrame(e.getStarttime(), e.getEndtime());
			//if it is empty
		    if(elist.isEmpty()){ 
		        edi.createEvent(e);
		    } else {
		    	if(elist.size() > 1) {//sort the events in the list so highest one if first
		    		Collections.sort(elist, new EventCompare());
		    	}
		    	//if not circle through them until all of replaced event has lowers pri than this one
		    	for(Event e1: elist) {
		    		//static event being replaced
		    		if(e1.getEventtype().equals("static")) {
		    			if(e1.getPriority() >= e.getPriority()) {
			    			return null;
			    		} else {
			    			//it the highest pri is lower than it, we can replace everything below
			    			templist.add(edi.deleteEvent(e1));
			    		}
		    		} else { //space for movable ones
		    			if(e1.getPriority() >= e.getPriority()) {
			    			//templist2.addAll(addEventMock(e1))s;
		    				if(templist2.size() > 1) {
		    					//sort them by high to low
		    					Collections.sort(templist2, new EventCompare());
		    				}
		    				for(Event e2: templist2) {
		    					if (e2.getPriority() >= e.getPriority()) {
		    						return null;
		    					} else {
		    						templist.add(e2);
		    					}
		    				}
			    		} else {
			    			templist.add(edi.deleteEvent(e1));
			    		}
		    		}
		    	}
		    	for(Event e3 : templist) {
		    		templist.remove(addEvent(e3));
		    	}
		    	thelist.add(edi.createEvent(e));
		    	return e;
		    }
		} 
//		else if (e.getEventtype().equals("movable")){
//			edi.createEvent(e);
//		}
//			List<Event> elist = edi.getEventsByTimeFrame(e.getInputtime(), e.getDuetime());
//			if(elist.isEmpty()) {
//				e.setStarttime(e.getInputtime());
//				//e.setEndtime(new Date(e.getInputtime().getTime() + e.getEventlength().getTime()));
//				edi.createEvent(e);
//			}
//		}
//			if(isFree(e.getInputtime().getTime(), e.getDuetime().getTime(), e.getEventlength().getTime(), elist)) {
//				e.setStarttime(new Date(this.st));
//				e.setEndtime(new Date(this.et));
//				edi.createEvent(e);
//			} else {
//				if(elist.size() > 1) {
//					//sort event from high to low pri
//					Collections.sort(elist, new EventCompare());
//				}
//				for (Event e4: elist) {
//					if(e4.getEventtype().equals("movable")) {
//						if(e4.getPriority() >= e.getPriority()) {
//			    			int a = e4.getEventlength().compareTo(e.getEventlength());
//			    			if(a < 0) {
//			    				return null;
//			    			} else {
//			    				return null;
//			    			}
//			    		} else {
//			    			//it the highest pri is lower than it, we can replace everything below
//			    			if(elist.size() > 1) {
//								//sort event from high to low pri
//								Collections.sort(elist, new EventCompare2());
//							}
//			    			templist.add(edi.deleteEvent(e4));
//			    			elist.remove(e4);
//			    			if(isFree(e.getInputtime().getTime(), e.getDuetime().getTime(), e.getEventlength().getTime(), elist)) {
//			    				e.setStarttime(new Date(this.st));
//			    				e.setEndtime(new Date(this.et));
//			    				for(Event e7: templist) {
//			    					templist.remove(addEvent(e7));
//			    				}
//			    				edi.createEvent(e);
//			    			}
//			    		}
//					} else if(e4.getEventtype().equals("static")) {
//						if(e4.getPriority() >= e.getPriority()) {
//							return null;
//						} else {
//							if(elist.size() > 1) {
//								//sort event from high to low pri
//								Collections.sort(elist, new EventCompare2());
//							}
//							templist.add(edi.deleteEvent(e4));
//							elist.remove(e4);
//							if(isFree(e.getInputtime().getTime(), e.getDuetime().getTime(), e.getEventlength().getTime(), elist)) {
//								e.setStarttime(new Date(this.st));
//								e.setEndtime(new Date(this.et));
//								for(Event e7: templist) {
//			    					templist.remove(addEvent(e7));
//			    				}
//								edi.createEvent(e);
//							}
//						}
//					}
//				}
//			}
//		}
		edi.createEvent(e);
		return e;
	}
	
//	public boolean isFree(long st, long et, long at, List<Event> elist) {
//		for(Event e: elist) {
//			if((st - e.getStarttime().getTime()) >= at) {
//				this.st = st;
//				this.et = e.getStarttime().getTime();
//				return true;
//			}
//			st = e.getEndtime().getTime();
//		}
//		if((st - et) > at) {
//			this.st = st;
//			this.et = et;
//			return true;
//		}
//		return false;
//	}
}