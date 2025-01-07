package com.tapfoods.model;

public class OrderTable {

	private int orderId;
	private int userId;
	private int restaurantId;
	private String orderDate;
	private double totalAmount;
	private String status;
	private String paymentMethod;
	
	public OrderTable() {
		super();
	}

	public OrderTable(int userId, int restaurantId, String orderDate, double totalAmount, String status,
			String paymentMethod) {
		super();
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMethod = paymentMethod;
	}

	public OrderTable(int orderId, int userId, int restaurantId, String orderDate, double totalAmount, String status,
			String paymentMethod) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMethod = paymentMethod;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "OrderTable [orderId=" + orderId + ", userId=" + userId + ", restaurantId=" + restaurantId
				+ ", totalAmount=" + totalAmount + ", status=" + status + ", paymentMethod=" + paymentMethod + "]";
	}
	
	
}
