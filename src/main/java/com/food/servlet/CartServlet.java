package com.food.servlet;

import java.io.IOException;

import com.tapfoods.daoimpl.MenuDAOImpl;
import com.tapfoods.model.Cart;
import com.tapfoods.model.CartItem;
import com.tapfoods.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();			// model - pojo class
            session.setAttribute("cart", cart);
        }

        
        // Action is performing
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addItemToCart(request, cart);
        } else if ("update".equals(action)) {
            updateCartItem(request, cart);
        } else if ("remove".equals(action)) {
            removeItemFromCart(request, cart);
        } else if ("clear".equals(action)) {
            cart.clear();
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    private void addItemToCart(HttpServletRequest request, Cart cart) {
        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            MenuDAOImpl menuDAO = new MenuDAOImpl();
            Menu menuItem = menuDAO.getMenu(itemId);

            if (menuItem != null) {
                CartItem item = new CartItem(
                        menuItem.getMenuId(),
                        menuItem.getRestaurantId(),
                        menuItem.getItemName(),
                        menuItem.getPrice(),
                        quantity
                );
                cart.addItem(item);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void updateCartItem(HttpServletRequest request, Cart cart) {
        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            if (quantity > 0) {
                cart.updateItem(itemId, quantity);
            } else {
                cart.removeItem(itemId);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void removeItemFromCart(HttpServletRequest request, Cart cart) {
        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            cart.removeItem(itemId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
