<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tapfoods.model.Menu, com.tapfoods.dao.MenuDAO, com.tapfoods.daoimpl.MenuDAOImpl, java.util.List" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.tapfoods.model.User" %>
<%@ page import="com.tapfoods.model.Restaurant" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <style>
        /* Add your styles here for the menu page */
    </style>
</head>
<body>

<%
    // Fetch the restaurant ID from the request
    String restaurantIdParam = request.getParameter("restaurantId");
    if (restaurantIdParam != null && !restaurantIdParam.isEmpty()) {
        int restaurantId = Integer.parseInt(restaurantIdParam);
        
        // Use MenuDao to get the list of menu items based on the restaurant ID
        MenuDAO menuDao = new MenuDAOImpl();
        List<Menu> menuList = menuDao.getMenuByRestaurantId(restaurantId);

        // Set the menu list in session
        session.setAttribute("menuList", menuList);
        session.setAttribute("restaurantId",restaurantId);
        System.out.println(session);
    }
    
   

%>

<%
    // Redirect to menu.jsp to display the menu list
    response.sendRedirect("menu.jsp");
%>

</body>
</html>
