package com.tapfoods.dao;

import com.tapfoods.model.OrderTable;

public interface OrderTableDAO {
    int insertOrder(Order order); // This method will insert an order into the database and return the generated order ID
}
