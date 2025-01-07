package com.tap.controller;

import java.io.IOException;
import java.util.List;

import com.tapfoods.dao.RestaurantDAO;
import com.tapfoods.daoimpl.RestaurantDAOImpl;
import com.tapfoods.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@SuppressWarnings("serial")
@WebServlet("/home")
public class HomeServlet extends HttpServlet {					// servlet to get all restaurants
	
	RestaurantDAO restaurantDAO;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 restaurantDAO = new RestaurantDAOImpl();
		
		List <Restaurant> restaurantsList = restaurantDAO.getAllRestaurants();
		
		 HttpSession session = request.getSession();
		
		 session.setAttribute("restaurantsList",restaurantsList);
		
		response.sendRedirect("home.jsp");
	}

}





