<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.tapfoods.model.CartItem" %>
<%@ page import="com.tapfoods.model.Cart" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Inter', sans-serif;
            background-color: #f7f7fa;
            color: #333;
            line-height: 1.6;
            padding: 30px 20px;
        }

        header {
            background-color: #ff5722;
            color: white;
            text-align: center;
            padding: 10px 0;
            font-size: 1.5rem;
            letter-spacing: 1px;
            border-radius: 4px;
            margin-bottom: 30px;
        }

        main {
            background-color: white;
            padding: 30px;
            max-width: 1100px;
            margin: 0 auto;
            border-radius: 12px;
            box-shadow: 0 12px 30px rgba(0, 0, 0, 0.1);
        }

        .checkout-section {
            margin-bottom: 40px;
            border-top: 5px solid #ff5722;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
            width: 80%;
        }

        .checkout-section h3 {
            font-size: 1.6rem;
            margin-bottom: 20px;
            font-weight: bold;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 30px;
        }

        table th,
        table td {
            padding: 16px;
            text-align: left;
            font-size: 1.1rem;
            border-bottom: 1px solid #ddd;
        }

        table th {
            background-color: #ff5722;
            color: white;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .total {
            font-size: 1.4rem;
            font-weight: bold;
            background-color: #f1f1f1;
            padding: 15px;
            border-radius: 8px;
            border-top: 3px solid #ff5722;
            color: #333;
            text-align: right;
        }

        form {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            margin-top: 20px;
        }

        label {
            font-weight: bold;
            font-size: 1rem;
            color: #555;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="number"],
        select {
            width: 90%;
            padding: 12px;
            font-size: 1rem;
            border: 1px solid #ddd;
            border-radius: 8px;
            background-color: #f9f9f9;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus,
        input[type="number"]:focus,
        select:focus {
            border-color: #ff5722;
            outline: none;
        }

        .btn {
            background-color: #ff5722;
            color: white;
            padding: 14px 20px;
            font-size: 1.1rem;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease;
            width: 100%;
        }

        .btn:hover {
            background-color: #e64a19;
            transform: translateY(-2px);
        }

        .btn:active {
            background-color: #d84315;
            transform: translateY(2px);
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 30px;
            color: #ff5722;
            font-size: 1rem;
            text-decoration: none;
            font-weight: 500;
            transition: color 0.3s;
        }

        .back-link:hover {
            text-decoration: underline;
            color: #e64a19;
        }

        @media (max-width: 768px) {
            form {
                grid-template-columns: 1fr;
            }

            table th,
            table td {
                font-size: 1rem;
            }

            .total {
                font-size: 1.2rem;
            }

            .back-link {
                font-size: 1rem;
            }
        }
    </style>
</head>
<body>
    <header>
        <h1>Checkout</h1>
    </header>

    <main>
        <%
            Cart cart = (Cart) session.getAttribute("cart");
            double total = 0;
            if (cart != null && !cart.getItems().isEmpty()) {
            	
        %>

        <!-- Order Summary Section -->
        <div class="checkout-section">
            <h3>Order Summary</h3>
            <table>
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Subtotal</th>
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
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

            <p class="total">Total: $<%= total %></p>
        </div>

        <!-- Shipping and Payment Form Section -->
        <div class="checkout-section">
            <h3>Enter Shipping Address and Payment Details</h3>
            <form action="placeOrder" method="POST">
                <!-- Shipping Information -->
                <label for="fullName">Full Name:</label>
                <input type="text" id="fullName" name="fullName" required>

                <label for="phoneNumber">Phone Number:</label>
                <input type="text" id="phoneNumber" name="phoneNumber" required>
                
                <label for="houseNo">Door No.:</label>
                <input type="text" id="doorNo" name="doorNo" required>

                <label for="area">Area:</label>
                <input type="text" id="area" name="area" required>

                <label for="city">City:</label>
                <input type="text" id="city" name="city" required>

                <label for="state">State:</label>
                <input type="text" id="state" name="state" required>
                
                 <label for="pincode">Pincode:</label>
                <input type="text" id="pincode" name="pincode"required>

                <!-- Payment Method -->
                <label for="paymentMethod">Payment Method:</label>
                <select name="paymentMethod" id="paymentMethod" required>
                    <option value="PhonePe">PhonePe</option>
                    <option value="Google Pay">Google Pay</option>
                    <option value="Paytm">Paytm</option>
                    <option value="UPI">UPI</option>
                </select>

                <input type="submit" value="Confirm Order" class="btn">
            </form>
        </div>

        <a href="home.jsp" class="back-link">Back to Menu</a>

        <%
            } else {
        %>
            <p>Your cart is empty. <a href="home.jsp" class="back-link">Go to Menu</a></p>
        <%
            }
        %>
    </main>
</body>
</html>
