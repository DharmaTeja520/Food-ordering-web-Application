<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Profile</title>
    <style>
        /* Add some simple styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        form {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }
        input, select {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            background: #4CAF50;
            color: #fff;
            cursor: pointer;
            border: none;
            padding: 12px;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background: #45a049;
        }
        .message {
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <form action="updateUserProfile" method="POST">
        <h1>Update Profile</h1>
        <input type="hidden" name="userId" value="${user.userId}" required>
        
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${user.username}" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${user.email}" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="${user.password}" required>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${user.address}" required>

        <label for="role">Role:</label>
        <select id="role" name="role">
            <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>Admin</option>
            <option value="user" ${user.role == 'user' ? 'selected' : ''}>User</option>
        </select>

        <input type="submit" value="Update Profile">

        <!-- Display messages -->
        <div class="message">
            <% if (request.getAttribute("successMessage") != null) { %>
                <p style="color: green;"><%= request.getAttribute("successMessage") %></p>
            <% } %>
            <% if (request.getAttribute("errorMessage") != null) { %>
                <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
            <% } %>
        </div>
    </form>
</body>
</html>
