<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/form.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .form-container {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            margin-bottom: 20px;
        }
        td {
            padding: 4px 8px;  /* Reduced padding to decrease space */
            text-align: left;
        }
        label {
            font-weight: bold;
            color: #333;
            margin-bottom: 4px;  /* Reduced bottom margin */
            display: block;  /* Ensures label takes up full width */
        }
        input[type="text"], input[type="email"], input[type="password"], select {
            width: 100%;
            padding: 8px;  /* Reduced padding to bring input closer */
            margin: 3px 0;  /* Reduced margin */
            border-radius: 4px;
            border: 1px solid #ddd;
            font-size: 14px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        p {
            text-align: center;
            font-size: 14px;
        }
        a {
            color: #007BFF;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        .error-message {
            color: red;
            text-align: center;
            font-size: 14px;
            margin-top: 10px;
        }
        #role {
    		cursor: pointer;
		}
		
        
        
    </style>
</head>
<body>
    <h1>Sign Up</h1>
    <div class="form-container">
        <form action="registerServlet" method="post">

            <table>
                <tr>
                    <td><label for="username">Username:</label></td>
                    <td><input type="text" id="username" name="username" required></td>
                </tr>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="email" id="email" name="email" required></td>
                </tr>
                <tr>
                    <td><label for="password">Password:</label></td>
                    <td><input type="password" id="password" name="password" required></td>
                </tr>
                <tr>
                    <td><label for="confirmPassword">Confirm Password:</label></td>
                    <td><input type="password" id="confirmPassword" name="confirmPassword" required></td>
                </tr>
                <tr>
                    <td><label for="address">Address:</label></td>
                    <td><input type="text" id="address" name="address" required></td>
                </tr>
                <tr>
                    <td><label for="role">Role:</label></td>
                    <td>
                        <select id="role" name="role" required>
                            <option value="customer">Customer</option>
                            <option value="admin">Admin</option>
                            <option value="chef">Chef</option>
                            <option value="delivery">Delivery</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <input type="submit" value="Sign Up">
                    </td>
                </tr>
            </table>
        </form>

        <!-- Display error or success message -->
        <% if (request.getAttribute("message") != null) { %>
            <p class="error-message"><%= request.getAttribute("message") %></p>
        <% } %>
        <p>Already have an account? <a href="login.jsp">Login here</a>.</p>
    </div>
</body>
</html>
