<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tapfoods.model.OrderHistory, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h2 {
            color: #ff5722;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #ff5722;
            color: white;
        }

        .no-history {
            margin: 20px 0;
            font-size: 16px;
            color: #888;
        }

        .no-history a {
            color: #ff5722;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <h2>Your Order History</h2>
    <%
        List<OrderHistory> historyList = (List<OrderHistory>) session.getAttribute("historyList");
        if (historyList != null && !historyList.isEmpty()) {
    %>
        <table>
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>Total Amount</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (OrderHistory history : historyList) {
                %>
                <tr>
                    <td><%= history.getOrderId() %></td>
                    <td><%= history.getOrderDate() %></td>
                    <td>$<%= history.getTotalAmount() %></td>
                    <td>
                        <a href="OrderItemServlet?orderId=<%= history.getOrderId() %>">View</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <p class="no-history">No order history available. 
            <a href="menu.jsp">Browse our menu and place your first order!</a>
        </p>
    <%
        }
    %>
</body>
</html>
