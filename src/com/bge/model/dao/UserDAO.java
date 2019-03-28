package com.bge.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bge.exception.DBException;
import com.bge.model.beans.User;

public class UserDAO extends DAO<User> {

	public UserDAO() throws DBException {
		super("user");
	}

	public User[] getAll() throws DBException {
		PreparedStatement stmt;
		ArrayList<User> users;

		try {
			stmt = this.connection.prepareStatement("SELECT * FROM users");
		} catch (SQLException e) {
			throw new DBException("Error getting the list of users");
		}

		users = find(stmt);

		return users.toArray(new User[users.size()]);
	}
	
	private ArrayList<User> find(PreparedStatement stmt) throws DBException {
		ArrayList<User> users = new ArrayList<User>();

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String firstName = rs.getString("first_name");
				String password = rs.getString("password");
				String email = rs.getString("email");

				User user = new User(id, name, firstName, password, email);

				users.add(user);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new DBException("Error getting the users");
		}

		return users;
	}
	
	public <T> User[] find(String field, T value) throws DBException {
		String sql = "SELECT * FROM users WHERE " + field + " = ?";
		PreparedStatement stmt;
		ArrayList<User> users;

		try {
			stmt = this.connection.prepareStatement(sql);

			if (value.getClass().equals(String.class)) {
				stmt.setString(1, (String) value);
			} else if (value.getClass().equals(Integer.class)) {
				stmt.setInt(1, (int) value);
			} else {
				stmt.setObject(1, value);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Error getting the users");
		}

		users = find(stmt);

		return users.toArray(new User[users.size()]);
	}
	
	public <T> User findOne(String field, T value) throws DBException {
		User[] users = find(field, value);

		return (users.length > 0) ? users[0] : null;
	}

	public User find(int id) throws DBException {
		return findOne("id", id);
	}
	
	public User findByEmail(String email) throws DBException {
		return findOne("email", email);
	}

	public void insert(User user) throws DBException {
		String sql = "INSERT INTO users(name, first_name, password, email) VALUES(?, ?, ?, ?)";

		try {
			PreparedStatement pstmt = this.connection.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getEmail());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new DBException("Error adding the user");
		}
	}

	public void delete(User user) throws DBException {
		delete(user.getId());
	}

	public void update(User user) throws DBException {
		String sql = "UPDATE users SET name = ?, first_name = ?, password = ?, email = ? WHERE id = ?";

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getFirstName());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getEmail());
			stmt.setInt(5, user.getId());

			if (stmt.executeUpdate() == 0) {
				throw new DBException(
						"Error: The user was not present in the database and thus cannot be updated.");
			}

			stmt.close();
		} catch (SQLException e) {
			throw new DBException("Error updating the user");
		}
	}

}
