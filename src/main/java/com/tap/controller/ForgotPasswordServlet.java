package com.tap.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tapfoods.dbUtils.DBUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/forgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Database connection
        	conn = DBUtils.myConnect();

            // Check if username exists
            String checkUserQuery = "SELECT userId FROM user WHERE username = ?";
            stmt = conn.prepareStatement(checkUserQuery);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Update password
                String updateQuery = "UPDATE user SET password = ? WHERE username = ?";
                stmt = conn.prepareStatement(updateQuery);
                stmt.setString(1, newPassword);
                stmt.setString(2, username);
                stmt.executeUpdate();

                request.setAttribute("successMessage", "Password updated successfully.");
            } else {
                request.setAttribute("errorMessage", "Username not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred. Please try again.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
    }
}
