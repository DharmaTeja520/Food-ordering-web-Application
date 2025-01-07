<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tapfoods.model.OrderItem, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Items</title>
</head>
<body>
    <h2>Order Items</h2>
    <%
        List<OrderItem> orderItemList = (List<OrderItem>) session.getAttribute("orderItemList");
        if (orderItemList != null && !orderItemList.isEmpty()) {
    %>
        <table border="1">
            <thead>
                <tr>
                    <th>Item Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
            <%
                for (OrderItem item : orderItemList) {
            %>
                <tr>
                    <td><%= item.getItemName() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td>$<%= item.getPrice() %></td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <p>No items found for this order.</p>
    <%
        }
    %>
</body>
</html>
