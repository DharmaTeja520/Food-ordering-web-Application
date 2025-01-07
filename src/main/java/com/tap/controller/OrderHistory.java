package com.tap.controller;

import java.io.IOException;
import java.util.List;

import com.tapfoods.daoimpl.OrderHistoryDAOImpl;
import com.tapfoods.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class OrderHistory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            int userId = loggedInUser.getUserId(); // Assuming `getUserId()` gives the user's ID
            OrderHistoryDAOImpl dao = new OrderHistoryDAOImpl();

            System.out.println("Fetching order history for user ID: " + userId); // Debugging

            // Fetch order history from the database
            List<com.tapfoods.model.OrderHistory> historyList = dao.getOrderHistory(userId);

            if (historyList == null || historyList.isEmpty()) {
                System.out.println("No order history found for user ID: " + userId);
            } else {
                System.out.println("Order history retrieved: " + historyList);
            }

            // Store the history list in session
            session.setAttribute("historyList", historyList);

            // Redirect to the JSP page
            response.sendRedirect("orderHistory.jsp");
        } else {
            // If the user is not logged in, redirect to login
            System.out.println("User not logged in. Redirecting to login page.");
            response.sendRedirect("login.jsp");
        }
    }
}
