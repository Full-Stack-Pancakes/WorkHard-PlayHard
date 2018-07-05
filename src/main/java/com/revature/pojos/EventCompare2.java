package com.revature.pojos;

import java.util.Comparator;

public class EventCompare2 implements Comparator<Event>{
	@Override
	public int compare(Event e1, Event e2) {
		int result;
		result = e2.getPriority() - e1.getPriority();
		if(result == 0) {
			result = e2.getStarttime().compareTo(e1.getStarttime());
			return result;
		}
		return result;
	}
}
