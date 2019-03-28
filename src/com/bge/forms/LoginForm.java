package com.bge.forms;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import com.bge.exception.DBException;
import com.bge.model.beans.User;
import com.bge.model.dao.UserDAO;

public class LoginForm {
	private String email;
	private String password;
	private String error;
	
	private User user;
	
	public LoginForm(String email, String password) {
		this.password = password;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
	        BigInteger hashNumber = new BigInteger(1, md.digest(password.getBytes()));
	        this.password = hashNumber.toString(16);
		}
		catch (NoSuchAlgorithmException e) {
			this.error = "Couldn't hash the password.";
		}
		
		this.email = email;
		
		check();
	}
	
	public LoginForm(HttpServletRequest request) {
		this(request.getParameter("email"), request.getParameter("pass"));
	}

	private void check() {
		try {
			UserDAO userDAO = new UserDAO();
			
			user = userDAO.findByEmail(email);
			
			if (user == null) {
				error = "This user doesn't exist.";
			}
			else if (!user.getPassword().equals(password)) {
				error = "Wrong password.";
			}
		} catch (DBException e) {
			error = "This user doesn't exist.";
		}
	}
	
	public String getError() {
		return error;
	}
	
	public User getUser() {
		return user;
	}
}
