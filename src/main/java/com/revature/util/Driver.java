package com.revature.util;

import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.revature.pojos.User;


@SpringBootApplication
public class Driver {

	public static void main(String[] args) {
//		Session s = HibernateUtil.getSession();
//		s.close();
		
		SpringApplication.run(Driver.class, args);
//		RestTemplate restTemplate = new RestTemplate();
		
//		String getRequestUrl = "http://localhost:8084/users";
//		User user = restTemplate.getForObject(getRequestUrl, User.class);
//		String postRequestUrl = "http://localhost:8084/users";
	}

}
