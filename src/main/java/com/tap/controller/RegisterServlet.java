package com.tap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/registerServlet")

public class RegisterServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/online_food_delivery";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Dharma";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Handle GET request, redirect to signup.jsp
        req.getRequestDispatcher("signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();

        // Handle POST request logic
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String address = req.getParameter("address");
        String role = req.getParameter("role");

        if (password.equals(confirmPassword) && password != null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                String sql = "INSERT INTO `user`(`username`, `email`, `password`, `address`, `role`) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, email);
                pstmt.setString(3, password);
                pstmt.setString(4, address);
                pstmt.setString(5, role);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    req.setAttribute("message", "Registration successful!  login now");
                } else {
                    req.setAttribute("message", "Registration failed. Please try again.");
                }
                connection.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                req.setAttribute("message", "Database error: " + e.getMessage());
            }
        } else {
            req.setAttribute("message", "Passwords do not match!");
        }

        req.getRequestDispatcher("signup.jsp").forward(req, resp);
    }
}
