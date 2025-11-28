package com.example.marketplace.web;

import java.io.IOException;

import com.example.marketplace.dao.UserDao;
import com.example.marketplace.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
	
	private final UserDao userDao = new UserDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		if (email == null || email.isBlank() || password == null || password.isBlank()) {
			req.setAttribute("error", "Email and password are required.");
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
			return;
		}
		
		User user = userDao.findByEmail(email);
		
		if (user == null || !password.equals(user.getPassword())) {
			req.setAttribute("error", "Invalid email or password");
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
			return;
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("currentUser", user);
		
		resp.sendRedirect(req.getContextPath() + "/profile");
		
	}

}
