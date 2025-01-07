package com.tap.controller;

import java.io.IOException;
import java.util.ArrayList;
import com.tapfoods.daoimpl.MenuDAOImpl;
import com.tapfoods.model.Menu;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/menuServlet")
public class MenuServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String restaurantIdParam = req.getParameter("restaurantId");

        if (restaurantIdParam != null && !restaurantIdParam.isEmpty()) {
            try {
                int restaurantId = Integer.parseInt(restaurantIdParam);

                // Use DAO to get the menu list based on restaurant ID
                MenuDAOImpl menuDAO = new MenuDAOImpl();
                ArrayList<Menu> menuList = (ArrayList<Menu>) menuDAO.getMenuByRestaurantId(restaurantId);

                // Set the menu list in the session
                HttpSession session = req.getSession();
                session.setAttribute("menuList", menuList);

                // Forward to menu.jsp
                req.getRequestDispatcher("menu.jsp").forward(req, res);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid restaurant ID");
            }
        } else {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Restaurant ID is required");
        }
    }
}
