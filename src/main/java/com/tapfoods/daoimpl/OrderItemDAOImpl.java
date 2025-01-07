package com.tapfoods.daoimpl;

import com.tapfoods.dbUtils.DBUtils;
import com.tapfoods.model.OrderItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl {

    public List<OrderItem> getItemsByOrderId(int orderId) {
        List<OrderItem> orderItemList = new ArrayList<>();
        String query = "SELECT * FROM order_items WHERE order_id = ?";

        try (Connection conn = DBUtils.myConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderItem item = new OrderItem();
                    item.setItemName(rs.getString("itemName"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setPrice(rs.getDouble("price"));
                    orderItemList.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }
}
