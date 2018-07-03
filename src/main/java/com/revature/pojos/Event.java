package com.revature.pojos;

import java.sql.Date;
import java.sql.Time;
import java.util.Comparator;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
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
	Date duetime;
	@Column
	String eventtype;
	@Column
	Boolean splitable;
	@Column
	Time minlength;
	@Column
	Date inputtime;
	@Column
	String dayofweek;
	@Column
	String location;
	@Column
	String description;
	@Column
	String timezone;
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Event(int priority, Time eventlength, Date starttime, Date duetime, String eventtype, Boolean splitable,
			Time minlength, Date inputtime, String dayofweek, String location, String description, String timezone) {
		super();
		this.priority = priority;
		this.eventlength = eventlength;
		this.starttime = starttime;
		this.duetime = duetime;
		this.eventtype = eventtype;
		this.splitable = splitable;
		this.minlength = minlength;
		this.inputtime = inputtime;
		this.dayofweek = dayofweek;
		this.location = location;
		this.description = description;
		this.timezone = timezone;
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
	public Date getInputtime() {
		return inputtime;
	}
	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}
	public String getDayofweek() {
		return dayofweek;
	}
	public void setDayofweek(String dayofweek) {
		this.dayofweek = dayofweek;
	}
	public Date getDuetime() {
		return duetime;
	}
	public void setDuetime(Date duetime) {
		this.duetime = duetime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	@Override
	public String toString() {
		return "Event [eventid=" + eventid + ", priority=" + priority + ", eventlength=" + eventlength + ", starttime="
				+ starttime + ", duetime=" + duetime + ", eventtype=" + eventtype + ", splitable=" + splitable
				+ ", minlength=" + minlength + ", inputtime=" + inputtime + ", dayofweek=" + dayofweek + ", location="
				+ location + ", description=" + description + ", timezone=" + timezone + "]";
	}
	
	
}
