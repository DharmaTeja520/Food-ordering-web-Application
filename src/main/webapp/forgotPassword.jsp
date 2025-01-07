<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create new Password</title>
    <link rel="stylesheet" href="css/form.css">
    <style>
        /* General body styling */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #e8f5e9; /* Soft green for a calming effect */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 0;
            background-image: linear-gradient(to right, #4caf50, #81c784); /* Gradient background */
        }

        /* Form container styling */
        form {
            background-color: #ffffff; /* White for good contrast */
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); /* Softer shadow for a modern feel */
            width: 100%;
            max-width: 400px;
            text-align: center; /* Center the content */
        }

        /* Header styling */
        h1 {
            font-size: 24px;
            font-weight: bold;
            color: #4caf50; /* Complementary green */
            margin-bottom: 20px;
        }

        /* Label styling */
        label {
            font-weight: bold;
            color: #333333; /* Darker color for readability */
            display: block;
            margin-bottom: 5px;
            text-align: left; /* Align text to the left for better form flow */
        }

        /* Input fields styling */
        input[type="text"], input[type="password"] {
            width: 100%; /* Full-width for uniformity */
            padding: 12px;
            margin-bottom: 15px;
            border: 1px solid #cccccc;
            border-radius: 6px;
            font-size: 16px;
            box-sizing: border-box; /* Avoid padding overflow */
            transition: border-color 0.3s ease; /* Smooth focus effect */
        }

        input[type="text"]:focus, input[type="password"]:focus {
            border-color: #4caf50; /* Highlighted green border on focus */
            outline: none;
        }

        /* Submit button styling */
        input[type="submit"] {
            background-color: #4caf50;
            color: #ffffff;
            padding: 12px 20px;
            border: none;
            border-radius: 6px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease; /* Smooth hover effect */
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #388e3c; /* Darker green on hover */
        }

        /* Error and success messages */
        .message-container {
            margin-top: 20px; /* Space between the form and the message */
            text-align: center; /* Center align messages */
        }

        .error-message, .success-message {
            font-size: 14px;
            padding: 10px;
            border-radius: 6px;
            width: 100%; /* Full-width */
            box-sizing: border-box;
        }

        .error-message {
            background-color: #ffcdd2; /* Light red for error background */
            color: #d32f2f; /* Red for error text */
            margin-top: 20px
        }

        .success-message {
            background-color: #c8e6c9; /* Light green for success background */
            color: #388e3c; /* Green for success text */
            margin-top: 20px
        }

        /* Media query for responsiveness */
        @media (max-width: 480px) {
            form {
                padding: 20px;
                max-width: 90%; /* Adjust width for smaller screens */
            }

            h1 {
                font-size: 20px;
            }

            input[type="submit"] {
                font-size: 16px;
            }
        }
        a {
			text-align: right;
			padding-top: 70px;
			text-decoration: none;
			
		}
		button {
			cursor: pointer;
			color: #ffffff;
			background-color: green;
			}
    </style>
</head>
<body>
    <form action="forgotPasswordServlet" method="POST">
        <h1>Create New Password</h1>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" placeholder="Enter your username" required>

        <label for="newPassword">New Password:</label>
        <input type="password" id="newPassword" name="newPassword" placeholder="Enter your new password" required>

        <input type="submit" value="Update Password">
        
       
    </form> 

    <!-- Message container displayed at the bottom -->
    <div class="message-container">
        <% if (request.getAttribute("errorMessage") != null) { %>
            <p class="error-message"><%= request.getAttribute("errorMessage") %></p>
        <% } %>
        <% if (request.getAttribute("successMessage") != null) { %>
            <p class="success-message"><%= request.getAttribute("successMessage") %></p>
        <% } %>
    </div>
</body>
</html>
