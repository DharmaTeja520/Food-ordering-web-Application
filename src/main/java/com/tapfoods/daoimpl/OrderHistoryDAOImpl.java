package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tapfoods.dbUtils.DBUtils;
import com.tapfoods.model.OrderHistory;

public class OrderHistoryDAOImpl {

    public List<OrderHistory> getOrderHistory(int userId) {
        List<OrderHistory> historyList = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id = ?";

        try (Connection conn = DBUtils.myConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderHistory history = new OrderHistory();
                    history.setOrderId(rs.getInt("orderId"));
                    history.setOrderDate(rs.getDate("orderDate"));
                    history.setTotalAmount(rs.getDouble("totalAmount"));

                    System.out.println("Order Found: " + history); // Debugging
                    historyList.add(history);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching order history: " + e.getMessage());
        }

        return historyList;
    }
}
