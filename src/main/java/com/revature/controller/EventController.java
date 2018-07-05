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
import com.revature.exception.UserNotFoundException;
import com.revature.pojos.Event;
import com.revature.pojos.EventCompare;
import com.revature.pojos.EventCompare2;
import com.revature.pojos.EventTimeCompare;
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
	public Event addEvents(@RequestBody Event event) {
		Event e = addEvent(event);
		if (e != event) {
			return event;
		}
		return null;
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
	
	public Event addEvent(Event e){
		   List<Event> tempevent = null;
		   Event ex = null;
		   tempevent.clear();
		   int count = 0;
		   EventDaoImpl edi = new EventDaoImpl();
		   //check the event type to be static or movable
		   if(e.getEventtype() == "static"){
		       //this function should be added to the eventdaoimpl, to see if the time period is free, it will return an list
		       //if that period is free, then it will return an empty list, else it will return the event in the list
			   java.sql.Date endtime = new Date(e.getStarttime().getTime() + e.getEventlength().getTime());
		       List<Event> elist = edi.getEventsByTimeFrame(e.getStarttime(), endtime);
		       if(elist.isEmpty()){ 
		           edi.createEvent(e);
		           return e;
		       }
		       else {
		           //if more than 1 event is replaced, sort them based on event sort
		           if(elist.size() > 1){
		               Collections.sort(elist, new EventCompare());
		           }
		           for (Event e1 : elist){
		               count = elist.size();
		               //compare the priority of events, starting from the lowest priority
		               if(e1.getPriority() >= e.getPriority()){
		                   if(e1.getEventtype() == "static"){
		                       return e;
		                   }
		                   else if (e1.getEventtype() == "movable"){
		                       //addEventMock is similar to addEvent, but it is a mock up for the addEvent, where an event will be returned
		                       //by recursively adding event until every event is added or the replaced event cannot replace any other event
		                       tempevent.addAll(addEventMock(e1));
		                       if(tempevent.size() > 1){
		                            Collections.sort(tempevent, new EventCompare());
		                       }
		                       for(Event e3 : tempevent){
		                    	   if(e3.getPriority() >= e.getPriority()){
		                               return e;
		                           }
		                           
		                       }
		                       //if all event is above priority on the added event, this will trigger
		                       if (count == 1){
		                            edi.createEvent(e);
		                            for(Event e2 : elist){
		                                //delete them from the database
		                                deleteEvent(e2.getEventid());
		                                elist.remove(e2);
		                                //add them back using the addEvent function
		                                ex = addEvent(e2);
		                            }
		                            return ex;
		                       }
		                       else {
		                           return e;
		                       }
		                   }
		               }
		               else {
		                   //if the event is less than the added event, remove it from tempevent, added to the list that needs to be solved
		                   for(Event e3 : tempevent){
		                    tempevent.remove(e3);
		                    elist.add(e3);
		                   }
		                    Collections.sort(elist, new EventCompare());
		                    edi.createEvent(e);
		                    for(Event e2 : elist){
		                        //delete them from the database
		                        deleteEvent(e2.getEventid());
		                        //add them back using the addEvent function
		                        ex = addEvent(e2);
		                    }
		                    return ex;
		               }
		           }
		       }
		   }
		   else if (e.getEventtype() == "movable") {
		       //find every event between now and the due time
			   java.sql.Date endtime = new Date(e.getStarttime().getTime() + e.getEventlength().getTime());
		       List<Event> elist = edi.getEventsByTimeFrame(e.getStarttime(), endtime);
		       //make a compare function that solely compares the time of each event starting
		       //and sort them by time, so we can find if there are periods of free time between 2 events for the new
		       //event to be added, not that sure about the specifics yet
		       Collections.sort(elist, new EventTimeCompare());
		       //temp function check if this is free
		       if(isFree(e.getInputtime().getTime(), elist, e.getDuetime().getTime(), e.getEventlength().getTime())){
		           edi.createEvent(e);
		           return ex;
		       }
		       else if(!isFree(e.getInputtime().getTime(), elist, e.getDuetime().getTime(), e.getEventlength().getTime())){
		           List<Event> elistmv = new ArrayList<Event>();
		           List<Event> elistst = new ArrayList<Event>();
		           List<Event> elistadd = new ArrayList<Event>();
		               for(Event e4 : elist){
		                   //seperate them into 2 lists, one is movable, one is static
		                   if(e4.getEventtype() == "movable"){
		                       elistmv.add(e4);
		                   }
		                   else if (e4.getEventtype() == "static"){
		                       elistst.add(e4);
		                   }
		               }
		               //similar to event compare, just in the other order, lowest pri to highest
		               Collections.sort(elistmv, new EventCompare2());
		               Collections.sort(elistst, new EventCompare2());
		               for(Event e5 : elistmv){
		                   if(e5.getPriority() < e.getPriority()){
		                       elistmv.remove(e5);
		                       elist.remove(e5);
		                       elistadd.add(e5);
		                       if(isFree(e.getInputtime().getTime(), elist, e.getDuetime().getTime(), e.getEventlength().getTime())){
		                           edi.createEvent(e);
		                           Collections.sort(elistadd, new EventCompare2());
		                           for(Event e6 : elistadd){
		                               ex = addEvent(e6);
		                           }
		                           return ex;
		                       }
		                   }
		                   else if (e5.getPriority() >= e.getPriority()){
		                       if(e5.getEventlength().getTime() < e.getEventlength().getTime()){
		                           tempevent.addAll(addEventMock(e5));
		                           Collections.sort(tempevent, new EventCompare());
		                           for(Event e7 : tempevent){
		                               if (e5.getPriority() < e.getPriority()){
		                                   elist.remove(e7);
		                                   edi.createEvent(e);
		                                   ex = addEvent(e7);
		                                   return ex;
		                               }
		                               else{
		                                   return e;
		                               }
		                           }
		                       }
		                       else{
		                           return e;
		                       }
		                   }
		               }
		                for(Event e5 : elistst){
		                    if(e5.getPriority() < e.getPriority()){
		                        elistst.remove(e5);
		                       elist.remove(e5);
		                       elistadd.add(e5);
		                    }
		                    if(isFree(e.getInputtime().getTime(), elist, e.getDuetime().getTime(), e.getEventlength().getTime())){
		                           edi.createEvent(e);
		                           Collections.sort(elistadd, new EventCompare2());
		                           for(Event e6 : elistadd){
		                               ex = addEvent(e6);
		                           }
		                           return ex;
		                     }
		                }
		        }
		       else{
		           return e;
		       }
		        
		   }
		return e;
	}
	public Boolean isFree(long st, List<Event> elist, long et, long ct){
		for(Event e: elist) {
			if((st - e.getStarttime().getTime()) > ct) {
				return true;
			}
			st = e.getDuetime().getTime();
		}
		if((st - et) > ct) {
			return true;
		}
		return false;
	}
	
	public List<Event> addEventMock(Event e){
		List<Event> tempevent = null;
		List<Event> returnlist = null;
		   Event ex = null;
		   tempevent.clear();
		   int count = 0;
		   EventDaoImpl edi = new EventDaoImpl();
		   //check the event type to be static or movable
		   if(e.getEventtype() == "static"){
		       //this function should be added to the eventdaoimpl, to see if the time period is free, it will return an list
		       //if that period is free, then it will return an empty list, else it will return the event in the list
			   java.sql.Date endtime = new Date(e.getStarttime().getTime() + e.getEventlength().getTime());
		       List<Event> elist = edi.getEventsByTimeFrame(e.getStarttime(), endtime);
		       if(elist.isEmpty()){ 
		           return returnlist;
		       }
		       else {
		           //if more than 1 event is replaced, sort them based on event sort
		           if(elist.size() > 1){
		               Collections.sort(elist, new EventCompare());
		           }
		           for (Event e1 : elist){
		               count = elist.size();
		               //compare the priority of events, starting from the lowest priority
		               if(e1.getPriority() >= e.getPriority()){
		                   if(e1.getEventtype() == "static"){
		                	   returnlist.add(e);
		                       return returnlist;
		                   }
		                   else if (e1.getEventtype() == "movable"){
		                       //addEventMock is similar to addEvent, but it is a mock up for the addEvent, where an event will be returned
		                       //by recursively adding event until every event is added or the replaced event cannot replace any other event
		                       tempevent.addAll(addEventMock(e1));
		                       if(tempevent.size() > 1){
		                            Collections.sort(tempevent, new EventCompare());
		                       }
		                       for(Event e3 : tempevent){
		                    	   if(e3.getPriority() >= e.getPriority()){
		                    		   returnlist.add(e);
		                               return returnlist;
		                           }
		                           
		                       }
		                       //if all event is above priority on the added event, this will trigger
		                       if (count == 1){
		                            edi.createEvent(e);
		                            for(Event e2 : elist){
		                                //delete them from the database
		                                deleteEvent(e2.getEventid());
		                                elist.remove(e2);
		                                //add them back using the addEvent function
		                                returnlist.addAll(addEventMock(e2));
		                            }
		                            return returnlist;
		                       }
		                       else {
		                    	   returnlist.add(e);
		                           return returnlist;
		                       }
		                   }
		               }
		               else {
		                   //if the event is less than the added event, remove it from tempevent, added to the list that needs to be solved
		                   for(Event e3 : tempevent){
		                    tempevent.remove(e3);
		                    elist.add(e3);
		                   }
		                    Collections.sort(elist, new EventCompare());
		                    edi.createEvent(e);
		                    for(Event e2 : elist){
		                        //delete them from the database
		                        deleteEvent(e2.getEventid());
		                        //add them back using the addEvent function
		                        returnlist.addAll(addEventMock(e2));
		                    }
		                    return returnlist;
		               }
		           }
		       }
		   }
		   else if (e.getEventtype() == "movable") {
		       //find every event between now and the due time
		       List<Event> elist = edi.getEventsByTimeFrame(e.getStarttime(), e.getDuetime());
		       //make a compare function that solely compares the time of each event starting
		       //and sort them by time, so we can find if there are periods of free time between 2 events for the new
		       //event to be added, not that sure about the specifics yet
		       Collections.sort(elist, new EventTimeCompare());
		       //temp function check if this is free
		       if(isFree(e.getInputtime().getTime(), elist, e.getDuetime().getTime(), e.getEventlength().getTime())){
		           return returnlist;
		       }
		       else if(!isFree(e.getInputtime().getTime(), elist, e.getDuetime().getTime(), e.getEventlength().getTime())){
		           List<Event> elistmv = new ArrayList<Event>();
		           List<Event> elistst = new ArrayList<Event>();
		           List<Event> elistadd = new ArrayList<Event>();
		               for(Event e4 : elist){
		                   //seperate them into 2 lists, one is movable, one is static
		                   if(e4.getEventtype() == "movable"){
		                       elistmv.add(e4);
		                   }
		                   else if (e4.getEventtype() == "static"){
		                       elistst.add(e4);
		                   }
		               }
		               //similar to event compare, just in the other order, lowest pri to highest
		               Collections.sort(elistmv, new EventCompare2());
		               Collections.sort(elistst, new EventCompare2());
		               for(Event e5 : elistmv){
		                   if(e5.getPriority() < e.getPriority()){
		                       elistmv.remove(e5);
		                       elist.remove(e5);
		                       elistadd.add(e5);
		                       if(isFree(e.getInputtime().getTime(), elist, e.getDuetime().getTime(), e.getEventlength().getTime())){
		                           edi.createEvent(e);
		                           Collections.sort(elistadd, new EventCompare2());
		                           for(Event e6 : elistadd){
		                        	   returnlist.addAll(addEventMock(e6));
		                           }
		                           return returnlist;
		                       }
		                   }
		                   else if (e5.getPriority() >= e.getPriority()){
		                       if(e5.getEventlength().getTime() < e.getEventlength().getTime()){
		                           tempevent.addAll(addEventMock(e5));
		                           Collections.sort(tempevent, new EventCompare());
		                           for(Event e7 : tempevent){
		                               if (e5.getPriority() < e.getPriority()){
		                                   elist.remove(e7);
		                                   edi.createEvent(e);
		                                   returnlist.addAll(addEventMock(e7));
		                                   return returnlist;
		                               }
		                               else{
		                            	   returnlist.add(e);
		                                   return returnlist;
		                               }
		                           }
		                       }
		                       else{
		                    	   returnlist.add(e);
		                           return returnlist;
		                       }
		                   }
		               }
		                for(Event e5 : elistst){
		                    if(e5.getPriority() < e.getPriority()){
		                        elistst.remove(e5);
		                       elist.remove(e5);
		                       elistadd.add(e5);
		                    }
		                    if(isFree(e.getInputtime().getTime(), elist, e.getDuetime().getTime(), e.getEventlength().getTime())){
		                           edi.createEvent(e);
		                           Collections.sort(elistadd, new EventCompare2());
		                           for(Event e6 : elistadd){
		                        	   returnlist.addAll(addEventMock(e6));
		                           }
		                           return returnlist;
		                     }
		                }
		        }
		       else{
		    	   returnlist.add(e);
		           return returnlist;
		       }
		        
		   }
		   returnlist.add(e);
		return returnlist;
	}
}
	
