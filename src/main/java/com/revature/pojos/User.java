package com.revature.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@Entity
@Table(name="CAL_USER")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="userSequence")
	@SequenceGenerator(allocationSize=1, name="userSequence", sequenceName="SQ_CalUser_PK")
	@Column(name="USERID")
	int userid;
	
	@Column
	String firstname;
	@Column
	String lastname;
	@Column
	String email;
	@Column
	String phone;
	@Column
	String password;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(	name="USER_EVENT",
				joinColumns = {@JoinColumn(name="USERID") },
				inverseJoinColumns = {@JoinColumn(name="EVENTID")} )
	List<Event> events = new ArrayList<Event>();
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String firstname, String lastname, String email, String phone, String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	public void addEvents(Event e) {
		this.events.add(e);
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", phone=" + phone + ", password=" + password + ", events=" + events + "]";
	}
	
}
