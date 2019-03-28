package com.bge.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bge.exception.DBException;
import com.bge.forms.RegisterForm;
import com.bge.model.dao.UserDAO;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FORM_URL = "/WEB-INF/register.jsp";
	private static final String OK_URL = "/WEB-INF/connected.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		RegisterForm formChecker = new RegisterForm(request);
		
		formChecker.getErrors().forEach((key, value) -> request.setAttribute(key + "Error", value));
		
		if (!request.getAttributeNames().hasMoreElements()) {
			try {
				UserDAO userDAO = new UserDAO();
				
				userDAO.insert(formChecker.getUser());
				
				url = OK_URL;
				
				userDAO.close();
				request.getSession().setAttribute("user", formChecker.getUser());
			}
			catch (DBException e) {
				e.printStackTrace();
				request.setAttribute("nameError", e.getMessage());
			}
		}
		
		request.setAttribute("user", formChecker.getUser());
		
		request.getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
