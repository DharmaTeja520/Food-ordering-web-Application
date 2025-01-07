<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tapfoods.model.Cart" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            color: #555;
            line-height: 1.6;
        }

        header {
            background-color: #ff5722;
            color: white;
            text-align: center;
            padding: 20px 0;
        }

        main {
            padding: 30px;
            max-width: 800px;
            margin: 0 auto;
            background-color: white;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            border-radius: 12px;
            text-align: center;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        p {
            font-size: 1.2rem;
            color: #444;
        }

        .tick-mark {
            margin: 20px 0;
        }

        .tick-mark img {
            width: 100px;
            height: 100px;
        }

        .btn {
            background-color: #ff5722;
            color: white;
            padding: 12px 25px;
            font-size: 1.2rem;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s;
            width: 20%;
            margin-top: 20px;
        }

        .btn:hover {
            background-color: #e64a19;
        }

        .btn:active {
            background-color: #d84315;
        }
    </style>
</head>
<body>

    <header>
        <h1>Order Confirmation</h1>
    </header>

    <main>
        <h2>Your order has been successfully placed. Thank you for shopping with us!</h2>

        <div class="tick-mark">
            <img src="Images/checked.png" alt="Order Confirmed">
        </div>

        <%
            // Clear the cart data from the session
            session.removeAttribute("cart");
        %>

        <form action="home.jsp" method="get">
            <input type="submit" value="Order More" class="btn">
        </form>
    </main>

</body>
</html>
