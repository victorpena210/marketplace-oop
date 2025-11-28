package com.example.marketplace.web;

import java.io.IOException;

import com.example.marketplace.dao.UserDao;
import com.example.marketplace.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
	
	private final UserDao userDao = new UserDao();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req,resp);
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String fullName = req.getParameter("fullName");
		
		if (email == null || email.isBlank() || password == null || password.isBlank() || fullName == null || fullName.isBlank()) {
			
			req.setAttribute("error", "All fields are required.");
			req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
			return;
		}
		
		if (userDao.findByEmail(email) != null) {
			req.setAttribute("error", "that email is already registerd.");
			req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
			return;
		}
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setFullName(fullName);
		
		userDao.save(user);
		
		resp.sendRedirect(req.getContextPath() + "/");
		
		
		
		
	}

}
