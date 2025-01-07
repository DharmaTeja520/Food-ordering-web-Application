<%@ page import="java.util.List" %>
<%@ page import="com.tapfoods.model.Menu , com.tapfoods.model.Restaurant" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Menu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #ff5722;
            color: #fff;
            padding: 15px;
            text-align: center;
        }

        .menu-list {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            margin: 20px;
            padding: 0;
        }

        .menu-item {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 15px;
            background-color: #fff;
            width: 250px;
            text-align: center;
            box-sizing: border-box; /* Ensure padding doesn't affect width */
            transition: transform 0.3s ease-in-out;
        }

        /* Hover effect for menu items */
        .menu-item:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .menu-item img {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
            
             height: 150px; /* Set a uniform height for all images */
             width: auto; /* Maintain aspect ratio */
             border-radius: 8px;
    		 object-fit: cover; /* Ensures the image fills the height without distortion */
        }

        .menu-item h3 {
            margin: 10px 0;
            color: #333;
            font-size: 18px;
        }

        .menu-item p {
            color: #666;
            font-size: 14px;
        }

        .menu-item .price {
            font-weight: bold;
            color: #ff5722;
            font-size: 16px;
        }
        
        
        
        
       form {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 10px;
    gap: 10px; /* Adds space between form elements */
}

.quantity-input {
    width: 60px;
    padding: 5px;
    border: 1px solid #ddd;
    border-radius: 4px;
    text-align: center;
}

.add-to-cart-btn {
    background-color: #28a745;
    color: #fff;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
    border-radius: 5px;
    font-weight: bold;
    font-size: 14px;
    transition: background-color 0.3s, transform 0.2s;
}

.add-to-cart-btn:hover {
    background-color: #218838;
    transform: scale(1.05); /* Slight zoom on hover */
}

.add-to-cart-btn:active {
    background-color: #1e7e34;
    transform: scale(1); /* Reset zoom on click */
}


       
       
       
       
       
       
        
        /* Responsive layout adjustments */
        @media (max-width: 768px) {
            .menu-item {
                width: 100%;  /* Full-width on smaller screens */
            }
        }
    </style>
</head>
<body>
    <header>
        <h1>Restaurant Menu</h1>
    </header>
    
    <main>
        <section class="menu-list">
            <%
                // Retrieve the list of menu items from the session
                List<Menu> menuList = (List<Menu>) session.getAttribute("menuList");
            
            int restaurantId = (Integer) session.getAttribute("restaurantId");
            
            


                if (menuList != null && !menuList.isEmpty()) {
                    for (Menu menu : menuList) {
            %>
                <div class="menu-item">
                    <img src="<%= menu.getImgpath() != null ? menu.getImgpath() : "default.jpg" %>" 
                         alt="<%= menu.getItemName() %>">
                    <h3><%= menu.getItemName() %></h3>
                    <p><%= menu.getDescription() %></p>
                    <p class="price">Price: $<%= menu.getPrice() %></p>
					
					<form action="cart" method="post">
						<input type="hidden" name="itemId" value="<%=menu.getMenuId()%>">
						Quantity: <input type="number" class="quantity-input" name="quantity" value="1" min="1" class="quantity-input"> 
								  <input type="submit" class="add-to-cart-btn" value="Add to Cart" >
								   <input type="hidden" name="action" value="add"> 
					</form>
					
                </div>
            <%
            }
            } else {
            %>
                <p>No menu items available.</p> <br><br>
            <%
                }
            %>
        </section>
    </main>
</body>
</html>
