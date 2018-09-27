package com.example.thymeleaf.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
	
	private String firstName;
	
	private String lastName;
	
	public Person() {}
	
	public Person(String firstNme, String lastName) {
		this.firstName = firstNme;
		this.lastName = lastName;
	}

}
