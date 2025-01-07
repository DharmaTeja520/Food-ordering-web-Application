package com.tap.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.tapfoods.dbUtils.DBUtils;
import com.tapfoods.model.Cart;
import com.tapfoods.model.CartItem;
import com.tapfoods.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
// @WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Retrieve cart and user details from session
        Cart cart = (Cart) session.getAttribute("cart");
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        // Ensure the loggedInUser and cart are not null
        if (loggedInUser == null || cart == null) {
            response.sendRedirect("login.jsp"); // Redirect if no user is logged in or cart is empty
            return;
        }

        // Retrieve userId from the loggedInUser object
        int userId = loggedInUser.getUserId();
        // Retrieve restaurantId from the session (make sure it is set elsewhere in the application)
        Integer restaurantId = (Integer) session.getAttribute("restaurantId");
        if (restaurantId == null) {
            response.sendRedirect("home"); // Redirect to the home page if restaurantId is not set
            return;
        }

        // Get order details from the form
     // Get user details from the form
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        // Get address details from the form
        String doorNo = request.getParameter("doorNo");
        String area = request.getParameter("area");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String pincode = request.getParameter("pincode");
        // Get payment details
        String paymentMethod = request.getParameter("paymentMethod");


        // Calculate total amount from the cart
        double totalAmount = 0;
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem item = entry.getValue();
            totalAmount += item.getPrice() * item.getQuantity();
        }

        // Database connection
        Connection conn = null;
        try {
            conn = DBUtils.myConnect();
            conn.setAutoCommit(false); // Start transaction

            
            String sql = "INSERT INTO address (fullName, phoneNumber, doorNo, area, city, state, pincode, paymentMethod) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.setString(3, doorNo);
            preparedStatement.setString(4, area);
            preparedStatement.setString(5, city);
            preparedStatement.setString(6, state);
            preparedStatement.setString(7, pincode);
            preparedStatement.setString(8, paymentMethod);

            // Execute the query
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Address successfully inserted into the database.");
            }
            
            
            // Get the latest orderId
            String orderIdQuery = "SELECT MAX(orderId) AS maxOrderId FROM `order`";
            PreparedStatement psOrderId = conn.prepareStatement(orderIdQuery);
            ResultSet rs = psOrderId.executeQuery();
            int orderId = 1;
            if (rs.next()) {
                orderId = rs.getInt("maxOrderId") + 1;
            }

            // Insert into the `order` table
            String insertOrderQuery = "INSERT INTO `order` (orderId, userId, restaurantId, orderDate, totalAmount, status, paymentMethod) " +
                                      "VALUES (?, ?, ?, NOW(), ?, 'Pending', ?)";
            PreparedStatement psOrder = conn.prepareStatement(insertOrderQuery);
            psOrder.setInt(1, orderId);
            psOrder.setInt(2, userId);
            psOrder.setInt(3, restaurantId);
            psOrder.setDouble(4, totalAmount);
            psOrder.setString(5, paymentMethod);
            psOrder.executeUpdate();

            // Insert order items into the `orderitem` table
            String insertOrderItemQuery = "INSERT INTO `orderitem` (orderId, menuId, quantity, subTotal) VALUES (?, ?, ?, ?)";
            PreparedStatement psOrderItem = conn.prepareStatement(insertOrderItemQuery);
            for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                CartItem item = entry.getValue();
                double subTotal = item.getPrice() * item.getQuantity();

                psOrderItem.setInt(1, orderId);
                psOrderItem.setInt(2, item.getItemId()); // Assuming each item has a unique ID (menuId)
                psOrderItem.setInt(3, item.getQuantity());
                psOrderItem.setDouble(4, subTotal);
                psOrderItem.executeUpdate();
            }

            // Insert into the `orderhistory` table
            String insertOrderHistoryQuery = "INSERT INTO `orderhistory` (userId, orderId, orderDate, total) " +
                                            "VALUES (?, ?, NOW(), ?)";
            PreparedStatement psOrderHistory = conn.prepareStatement(insertOrderHistoryQuery);
            psOrderHistory.setInt(1, userId);
            psOrderHistory.setInt(2, orderId);
            psOrderHistory.setDouble(3, totalAmount);
            psOrderHistory.executeUpdate();

            // Commit the transaction
            conn.commit();

            // Clear the cart session
            session.removeAttribute("cart");

            // Redirect to the order confirmation page
            response.sendRedirect("orderConfirm.jsp");

        } catch (SQLException e) {
            // Handle exceptions and rollback if an error occurs
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to error page on failure
        } finally {
            // Close resources
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Optionally handle GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("checkout.jsp"); // Redirect to the checkout page on GET requests
    }
}
