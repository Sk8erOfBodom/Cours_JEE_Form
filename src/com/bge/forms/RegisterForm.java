package com.bge.forms;

import java.util.Map;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.bge.model.beans.User;

public class RegisterForm {
	private String name;
	private String firstName;
	private String email;
	private String password;
	private String passwordConfirmation;

	private Map<String, String> errors;

	public RegisterForm(String name, String firstName, String password, String passwordConfirm, String email) {
		this.name = name.trim().toLowerCase();
		this.firstName = firstName.trim().toLowerCase();
		this.password = password;
		this.passwordConfirmation = passwordConfirm;
		this.email = email.trim().toLowerCase();

		this.errors = new HashMap<String, String>();

		check();
	}

	public RegisterForm(HttpServletRequest request) {
		this(request.getParameter("name"), request.getParameter("fname"), request.getParameter("pass"),
				request.getParameter("pass2"), request.getParameter("email"));
	}

	private void check() {
		if (name == null || name.length() < 3 || name.length() > 50) {
			errors.put("name", "Name must be between 3 and 50 characters.");
		}
		else if (!name.matches("^[a-zéèàçùâêîûôïöëäü -]+$")) {
			errors.put("name", "Name can only contain letters and hyphens.");
		}
		
		if (firstName == null || firstName.length() < 3 || firstName.length() > 25) {
			errors.put("fname", "First name must be between 3 and 25 characters.");
		}
		else if (!firstName.matches("^[a-zéèàçùâêîûôïöëäü-]+$")) {
			errors.put("fname", "First name can only contain letters and hyphens.");
		}
		
		if (password == null || password.length() < 6) {
			errors.put("pass", "Password must be at least 6 characters.");
		} else if (!password.matches(".*([A-Z].*[0-9])|([0-9].*[A-Z]).*")) {
			errors.put("pass", "Password must contain a number and uppercase letter.");
		}
		
		if (passwordConfirmation == null || ! passwordConfirmation.equals(password)) {
			errors.put("pass2", "Passwords don't match");
		}
		
		if (email == null || !email.matches("([a-z0-9_.-]*[a-z0-9]+)+@[a-z0-9-]+\\.[a-z]{2,}")) {
			errors.put("email", "Email address is invalid");
		}
	}

	public User getUser() {
		try {
			return new User(name, firstName, getHashedPassword(), email);
		}
		catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setUser(User user) {
		this.name = user.getName();
		this.firstName = user.getFirstName();
		this.password = user.getPassword();
	}

	public void setPasswordConfirm(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	public String getHashedPassword() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        BigInteger hashNumber = new BigInteger(1, md.digest(password.getBytes()));
        return hashNumber.toString(16);
	}
}
