package com.tapfoods.dao;

import java.util.ArrayList;
import com.tapfoods.model.Restaurant;

public interface RestaurantDAO {

	int addRestaurant(Restaurant r);

	ArrayList<Restaurant> getAllRestaurants();
	
	Restaurant getRestaurant(int restaurantId);		// get restaurant by id
	
	Restaurant updateRestaurant(Restaurant r);
	
	int deleteRestaurant(String name);

	
 }
