<%@ page import="java.util.List" %>
<%@ page import="com.tapfoods.model.OrderItem" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Items</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f4f4f4;
        }
    </style>
</head>
<body>

<h1>Order Items</h1>

<%
    List<OrderItem> orderItemList = (List<OrderItem>) session.getAttribute("orderItemList");
    
    if (orderItemList != null && !orderItemList.isEmpty()) {
%>
    <table>
        <thead>
            <tr>
                <th>Item ID</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (OrderItem item : orderItemList) {
            %>
            <tr>
                <td><%= item.getItemId() %></td>
                <td><%= item.getQuantity() %></td>
                <td>$<%= item.getPrice() %></td>
                <td>$<%= item.getQuantity() * item.getPrice() %></td>
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
