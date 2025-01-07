<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tapfoods.model.User, com.tapfoods.model.Restaurant, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Food Delivery</title>
    <style>
        /* Optional CSS for better layout and styling */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }
        header {
            background-color: #ff5722;
            color: #fff;
            padding: 15px;
            text-align: center;
        }
        nav a {
            color: #fff;
            text-decoration: none;
            margin: 0 10px;
        }
        .restaurant-list {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            margin: 20px;
        }
        .restaurant {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 15px;
            background-color: #fff;
            width: 250px;
            text-align: center;
            
            
        }
        .restaurant img {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
            
             height: 150px; /* Set a uniform height for all images */
             width: auto; /* Maintain aspect ratio */
             border-radius: 8px;
    		 object-fit: cover; /* Ensures the image fills the height without distortion */
        }
        .restaurant h3 {
            margin: 10px 0;
            color: #333;
        }
        .restaurant a {
            color: #ff5722;
            text-decoration: none;
        }
        
        /* Style for the 'View Menu' button */
.restaurant a {
    display: inline-block;
    margin-top: 15px;
    padding: 10px 20px;
    background-color: #ff5722;
    color: #fff;
    text-decoration: none;
    border-radius: 5px;
    font-weight: bold;
    text-align: center;
    transition: background-color 0.3s ease, transform 0.3s ease;
}

/* Hover effect for 'View Menu' button */
.restaurant a:hover {
    background-color: #e64a19; /* Darker shade of the button color */
    transform: translateY(-3px); /* Slight upward movement on hover */
}

h2{
	text-align: center;
	color: orange;
}


    </style>
</head>
<body>
    <header>
        <h1>Welcome to Online Food Delivery</h1>
        <nav>
            <a href="home">Home</a>
            <%
                // Fetch the logged-in user from session
                User loggedInUser = (User) session.getAttribute("loggedInUser");
            
            if (loggedInUser == null) {
                response.sendRedirect("login.jsp"); // Redirect if no user is logged in or cart is empty
            }

                if (loggedInUser != null) {
            %>
                <span>Welcome, <%= loggedInUser.getUsername() %>!</span>
                <a href="cart">View Cart</a>
                <a href="orderHistory">Order History</a>
                <a href="updateProfile.jsp">Update Profile</a>
                <a href="login.jsp">Logout</a>
            <%
                } else {
            %>
                <a href="login.jsp">Login</a>
                <a href="signup.jsp">Register</a>
            <%
                }
            %>
        </nav>
    </header>

    <main>
        <h2>Top Featured Restaurants</h2>
        <section class="restaurant-list">
            <%
                // Fetch the list of restaurants from session
                List<Restaurant> restaurantList = (List<Restaurant>) session.getAttribute("restaurantsList");
				
                if (restaurantList != null && !restaurantList.isEmpty()) {
                    // Iterate over the list of restaurants and display each one
                    for (Restaurant restaurant : restaurantList) {
            %>
                <div class="restaurant">
                    <!-- Ensure the correct attributes from the Restaurant model are used -->
                    
                    <img src="<%= restaurant.getImgPath() != null ? restaurant.getImgPath() : "default.jpg" %>"
                         alt="Image of <%= restaurant.getRestaurantName() != null ? restaurant.getRestaurantName() : "Restaurant" %>">
                         
                         
                    <h3><%= restaurant.getRestaurantName() != null ? restaurant.getRestaurantName() : "Unnamed Restaurant" %></h3>
                    <p>
                        <!-- Cuisine:  --><%= restaurant.getCuisineType() != null ? restaurant.getCuisineType() : "Unknown" %> -
                        <%= restaurant.getDeliveryTime() > 0 ? restaurant.getDeliveryTime() + " mins" : "Delivery time unavailable" %>
                        - <%=restaurant.getRating() %>
                    </p>
                    <a href="menuServlet.jsp?restaurantId=<%= restaurant.getRestaurantId() %>">View Menu</a>
	
                    
                </div>
            <%
                    }
                } else {
            %>
                <p>No restaurants available at the moment.</p>
            <%
                }
            %>
        </section>
    </main>
</body>
</html>
