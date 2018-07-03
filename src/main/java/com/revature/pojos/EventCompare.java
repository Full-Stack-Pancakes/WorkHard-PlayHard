package com.revature.pojos;

import java.util.Comparator;

public class EventCompare implements Comparator<Event> {
	
	@Override
	public int compare(Event e1, Event e2) {
		int result;
		result = e1.getPriority() - e2.getPriority();
		if(result == 0) {
			result = e1.getStarttime().compareTo(e2.getStarttime());
			return result;
		}
		return result;
	}
	
}
