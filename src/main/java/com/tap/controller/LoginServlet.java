package com.tap.controller;

import java.io.IOException;

import com.tapfoods.dao.UserDAO;
import com.tapfoods.daoimpl.UserDAOImpl;
import com.tapfoods.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    public LoginServlet() {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userDAO.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password) && user.getUsername().equals(username)) 
        {
            HttpSession session = req.getSession();
            session.setAttribute("loggedInUser", user);
            resp.sendRedirect("home.jsp");
        } 
        else 
        {
            req.setAttribute("errorMessage", "Invalid username or password");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}

