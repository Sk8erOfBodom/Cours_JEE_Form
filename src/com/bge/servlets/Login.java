package com.bge.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bge.forms.LoginForm;
import com.bge.model.beans.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FORM_URL = "/WEB-INF/login.jsp";
	private static final String OK_URL = "/WEB-INF/connected.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher(FORM_URL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = FORM_URL;
		User user;
		LoginForm loginForm = new LoginForm(request);
			
		if (loginForm.getError() == null) {
			url = OK_URL;
			user = loginForm.getUser();
		} else {
			user = new User();
			user.setEmail(request.getParameter("email"));
		}

		request.setAttribute("error", loginForm.getError());
		request.setAttribute("user", user);
		
		request.getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
