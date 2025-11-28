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

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/views/register.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");

        // 1) Basic validation
        if (email == null || email.isBlank()
                || password == null || password.isBlank()
                || fullName == null || fullName.isBlank()) {

            req.setAttribute("error", "All fields are required.");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp")
               .forward(req, resp);
            return;
        }

        // 2) Check if email already taken
        if (userDao.findByEmail(email) != null) {
            req.setAttribute("error", "That email is already registered.");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp")
               .forward(req, resp);
            return;
        }

        // 3) Create and save user
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);  // later: hash this
        user.setFullName(fullName);

        userDao.save(user);

        // 4) Load the saved user (so we have the generated ID, etc.)
        User saved = userDao.findByEmail(email);

        // 5) Auto-login: put user in session
        HttpSession session = req.getSession();
        session.setAttribute("currentUser", saved); // <— fixed name + defined

        // 6) Redirect: either home or profile
        // Option A: send them to profile
        resp.sendRedirect(req.getContextPath() + "/profile");

        // Option B (what you had): send them home
        // resp.sendRedirect(req.getContextPath() + "/");
    }
}
