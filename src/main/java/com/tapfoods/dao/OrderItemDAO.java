package com.tapfoods.dao;

import java.util.ArrayList;
import java.util.List;

import com.tapfoods.model.OrderItem;

public interface OrderItemDAO {

	OrderItem getOrderItem(int orderItemId);
	List<OrderItem> getItemsByOrderId(int orderId);
}
