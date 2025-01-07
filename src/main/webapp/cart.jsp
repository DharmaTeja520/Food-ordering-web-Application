<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.tapfoods.model.CartItem" %>
<%@ page import="com.tapfoods.model.Cart" %> 
<%@ page import="com.tapfoods.model.Menu" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #ff5722;
            color: #fff;
            padding: 15px;
            text-align: center;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        table th, table td {
            padding: 12px;
            text-align: center;
            border: 1px solid #ddd;
        }

        table th {
            background-color: #ff5722;
            color: #fff;
        }

        .action-buttons {
            display: flex;
            gap: 10px;
            justify-content: center;
        }

        .action-buttons form {
            display: inline-block;
        }

        .action-buttons input[type="submit"] {
            background-color: #ff5722;
            color: #fff;
            border: none;
            padding: 8px 12px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .action-buttons input[type="submit"]:hover {
            background-color: #e64a19;
        }

        .action-buttons a {
            background-color: #ff5722;
            color: #fff;
            border: none;
            padding: 8px 12px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-decoration: none;
        }

        .action-buttons a:hover {
            background-color: #e64a19;
        }

        .total {
            text-align: right;
            margin: 20px 10%;
            font-size: 18px;
            font-weight: bold;
        }

        .empty-cart {
            text-align: center;
            margin: 50px 0;
            font-size: 18px;
            color: #888;
        }

        .empty-cart a {
            background-color: #ff5722;
            color: #fff;
            border: none;
            padding: 8px 12px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-decoration: none;
        }

        .empty-cart a:hover {
            background-color: #e64a19;
        }

        .checkout-buttons {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 20px;
        }
        
        
        .checkout-buttons {
    display: flex;
    justify-content: center;
    gap: 20px;
    margin-top: 20px;
}

.checkout-buttons a,
.checkout-buttons input[type="submit"] {
    background-color: #ff5722;
    color: #fff;
    border: none;
    padding: 8px 15px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    text-decoration: none;
    font-size: 16px;
}

.checkout-buttons a:hover,
.checkout-buttons input[type="submit"]:hover {
    background-color: #e64a19;
}

.checkout-buttons input[type="submit"] {
    font-family: Arial, sans-serif;
    font-weight: bold;
}




        
        
        
    </style>
</head>
<body>
    <header>
        <h1>Shopping Cart</h1>
    </header>

    <main>
        <%
            Cart cart = (Cart) session.getAttribute("cart");		// get cart object details
            if (cart == null || cart.getItems().isEmpty()) {
        %>
            <p class="empty-cart">Your cart is empty. <a href="home.jsp">Go to Menu</a></p>
        <%
            } else {
                double total = 0;
        %>
            <table>
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Subtotal</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                            CartItem item = entry.getValue();
                            double subtotal = item.getPrice() * item.getQuantity();
                            total += subtotal;
                    %>
                        <tr>
                            <td><%= item.getItemName() %></td>
                            <td>$<%= item.getPrice() %></td>
                            <td><%= item.getQuantity() %></td>
                            <td>$<%= subtotal %></td>
                            <td class="action-buttons">
                                <!-- Update Quantity -->
                                <form action="cart" method="post">
                                    <input type="hidden" name="action" value="update">
                                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                                    <input type="number"  name="quantity" value="<%= item.getQuantity() %>" min="1">
                                    <input type="submit" value="Update">
                                </form>
                                <!-- Remove Item -->
                                <form action="cart" method="post">
                                    <input type="hidden" name="action" value="remove">
                                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                                    <input type="submit" value="Remove">
                                </form>
                            </td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <p class="total">Total: $<%= total %></p>

            <div class="checkout-buttons">
                <!-- Add More Items -->
                <a href="menu.jsp?restaurantId=<%= session.getAttribute("restaurantId") %>">Add More Items</a>
                

                <!-- Clear Cart -->
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="clear">
                    <input type="submit" value="Clear Cart">
                </form>
                
                <br><br>
                <!-- Proceed to Checkout -->
                <form action="checkout.jsp" method="post">
                    <input type="submit" value="Proceed to Checkout" class="btn proceed-to-checkout-btn">
                </form>
            </div>
        <%
            }
        %>
    </main>
</body>
</html>