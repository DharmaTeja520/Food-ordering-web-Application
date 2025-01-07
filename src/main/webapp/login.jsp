<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/form.css">
    
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            flex-direction: column; /* Stack elements vertically */
             background-image: url(http://7oom.ru/wp-content/uploads/foto-priroda-23.jpg);
        }

        h1 {
            text-align: center;
            color: #4CAF50;
            margin-bottom: 30px;
            width: 100%;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
            text-align: center; /* Center the form content */
        }

        table {
            width: 100%;
            margin-bottom: 20px;
        }

        td {
            padding: 10px;
        }

        label {
            font-weight: bold;
            color: #555;
        }

        input[type="text"], input[type="password"] {
            width: 80%;
            padding: 10px;
            margin: 5px 0 15px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            display: block;
            margin-left: auto;
            margin-right: auto;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        p {
            text-align: center;
            font-size: 14px;
            color: #ff0000;
        }

        .error-message {
            font-size: 14px;
            color: red;
            text-align: center;
        }

        .signup-link {
            text-align: center;
            font-size: 14px;
            margin-top: 15px;
        }

        .signup-link a {
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }

        .signup-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Login</h1>
    <form action="loginServlet" method="POST">
        <table>
            <tr>
                <td><label for="username">Username:</label></td>
                <td><input type="text" id="username" name="username" required></td>
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td><input type="password" id="password" name="password" required></td>
            </tr>
            <tr>
                <td><input type="submit" value="Login"></td>
            </tr>
        </table>
        
    </form>
    
    <div class="signup-link">
   		 <p><a href="forgotPassword.jsp">Forgot Password?</a></p>
	</div>
    
    
    <% if (request.getAttribute("errorMessage") != null) { %>
        <p class="error-message"><%= request.getAttribute("errorMessage") %></p>
    <% } %>
    
    <div class="signup-link">
        <p>Don't have an account? <a href="signup.jsp">Sign up here</a></p>
    </div>
    
</body>
</html>
