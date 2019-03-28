package com.bge.model.beans;

public class User {
	private int id;
	private String name;
	private String firstName;
	private String password;
	private String email;
	
	public User() {
	}
	
	public User(String name, String firstName, String password) {
		this(0, name, firstName, password, "");
	}
	
	public User(String name, String firstName, String password, String email) {
		this(0, name, firstName, password, email);
	}
	
	public User(int id, String name, String firstName, String password, String email) {
		this.id = id;
		this.name = capitalize(name);
		this.firstName = capitalize(firstName);
		this.password = password;
		this.email = email;
	}
	
	private String capitalize(String name) {
		if (name.isEmpty()) {
			return name;
		}
		
		String[] array = name.split("-");
		
		for (int i = 0; i < array.length; i++) {
			String[] subarray = array[i].split(" ");
			
			for (int j = 0; j < subarray.length; j++) {
				subarray[j] = subarray[j].substring(0, 1).toUpperCase() + subarray[j].substring(1).toLowerCase();
			}
			
			array[i] = String.join(" ", subarray);
		}
		
		return String.join("-", array);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setName(String name) {
		this.name = capitalize(name);
	}

	public void setFirstName(String firstName) {
		this.firstName = capitalize(firstName);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
