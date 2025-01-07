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

@WebServlet("/updateUserProfile")
public class UpdateUserProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle retrieving user data for the form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");

        if (userIdStr == null || userIdStr.isEmpty()) {
            request.setAttribute("errorMessage", "User ID is missing.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        try {
            int userId = Integer.parseInt(userIdStr);

            // Fetch user details from the database
            String sql = "SELECT username, email, password, address, role FROM user WHERE userId = ?";
            try (Connection conn = DBUtils.myConnect(); 
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, userId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        // Set the user details as request attributes
                        request.setAttribute("userId", userId);
                        request.setAttribute("username", rs.getString("username"));
                        request.setAttribute("email", rs.getString("email"));
                        request.setAttribute("password", rs.getString("password")); // Consider masking or not exposing this
                        request.setAttribute("address", rs.getString("address"));
                        request.setAttribute("role", rs.getString("role"));
                    } else {
                        request.setAttribute("errorMessage", "User not found.");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                        return;
                    }
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid User ID format.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        // Forward to the JSP to display the form with the user's details
        request.getRequestDispatcher("updateProfile.jsp").forward(request, response);
    }

    // Handle updating user data
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve and validate the `userId` parameter
            String userIdStr = request.getParameter("userId");
            if (userIdStr == null || userIdStr.isEmpty()) {
                throw new IllegalArgumentException("User ID is missing or empty.");
            }
            int userId = Integer.parseInt(userIdStr);

            // Retrieve other form data
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String role = request.getParameter("role");

            // Update logic
            String sql = "UPDATE user SET username = ?, email = ?, password = ?, address = ?, role = ? WHERE userId = ?";

            try (Connection conn = DBUtils.myConnect(); 
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, username);
                pstmt.setString(2, email);
                pstmt.setString(3, password); // Ideally hash the password here
                pstmt.setString(4, address);
                pstmt.setString(5, role);
                pstmt.setInt(6, userId);

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    request.setAttribute("successMessage", "User profile updated successfully!");
                } else {
                    request.setAttribute("errorMessage", "Failed to update user profile. User not found.");
                }
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
        }

        // Forward to the JSP
        request.getRequestDispatcher("updateProfile.jsp").forward(request, response);
    }
}
