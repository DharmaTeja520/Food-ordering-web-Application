package com.tapfoods.model;

import java.sql.Date;

public class OrderHistory {
    private int orderHistoryId;
    private int orderId;
    private int userId;
    private Date orderDate;
    private float totalAmount;
    private String status;

    public OrderHistory() {
        super();
    }

    public OrderHistory(int orderId, int userId, Date orderDate, float totalAmount, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryId(int orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = (float) totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderHistory [orderId=" + orderId + ", userId=" + userId + ", orderDate=" + orderDate +
               ", totalAmount=" + totalAmount + ", status=" + status + "]";
    }
}
