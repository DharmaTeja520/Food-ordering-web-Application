package com.tap.controller;

import java.io.IOException;
import java.util.List;

import com.tapfoods.daoimpl.OrderItemDAOImpl;
import com.tapfoods.model.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class OrderItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderItemDAOImpl dao = new OrderItemDAOImpl();
        List<OrderItem> orderItemList = dao.getItemsByOrderId(orderId);

        HttpSession session = request.getSession();
        session.setAttribute("orderItemList", orderItemList);
        response.sendRedirect("displayList.jsp");
    }
}
