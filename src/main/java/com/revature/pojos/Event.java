package com.revature.pojos;

import java.sql.Date;
import java.sql.Time;
import java.util.Comparator;

import javax.persistence.*;

@Entity
@Table
public class Event {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="eventSequence")
	@SequenceGenerator(allocationSize=1, name="eventSequence", sequenceName="SQ_CalEvent_PK")
	@Column(name="EVENTID")
	int eventid;
	
	@Column
	int priority;
	@Column
	Time eventlength;
	@Column
	Date starttime;
	@Column
	String eventtype;
	@Column
	Boolean splitable;
	@Column
	Time minlength;
	@Column
	Time inputtime;
	@Column
	String dayofweek;
	@Column
	Date startdate;
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Event(int priority, Time eventlength, Date starttime, String eventtype, Boolean splitable,
			Time minlength, Time inputtime, String dayofweek, Date startdate) {
		super();
		this.priority = priority;
		this.eventlength = eventlength;
		this.starttime = starttime;
		this.eventtype = eventtype;
		this.splitable = splitable;
		this.minlength = minlength;
		this.inputtime = inputtime;
		this.dayofweek = dayofweek;
		this.startdate = startdate;
	}
	public int getEventid() {
		return eventid;
	}
	public void setEventid(int eventid) {
		this.eventid = eventid;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Time getEventlength() {
		return eventlength;
	}
	public void setEventlength(Time eventlength) {
		this.eventlength = eventlength;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public String getEventtype() {
		return eventtype;
	}
	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}
	public Boolean getSplitable() {
		return splitable;
	}
	public void setSplitable(Boolean splitable) {
		this.splitable = splitable;
	}
	public Time getMinlength() {
		return minlength;
	}
	public void setMinlength(Time minlength) {
		this.minlength = minlength;
	}
	public Time getInputtime() {
		return inputtime;
	}
	public void setInputtime(Time inputtime) {
		this.inputtime = inputtime;
	}
	public String getDayofweek() {
		return dayofweek;
	}
	public void setDayofweek(String dayofweek) {
		this.dayofweek = dayofweek;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	
	@Override
	public String toString() {
		return "Event [eventid=" + eventid + ", priority=" + priority + ", eventlength=" + eventlength + ", starttime="
				+ starttime + ", eventtype=" + eventtype + ", splitable=" + splitable + ", minlength=" + minlength
				+ ", inputtime=" + inputtime + ", dayofweek=" + dayofweek + ", startdate=" + startdate + "]";
	}
}
