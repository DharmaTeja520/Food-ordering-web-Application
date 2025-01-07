package com.tapfoods.dao;

import java.util.ArrayList;

import com.tapfoods.model.OrderHistory;

public interface OrderHistoryDAO {

	ArrayList<OrderHistory> getAllOrderHistory(int orderHistoryId);
	OrderHistory getOrderHistory(int orderHistoryId);
	ArrayList<OrderHistory> getOrderHistoryByUserId(int userId);
}
