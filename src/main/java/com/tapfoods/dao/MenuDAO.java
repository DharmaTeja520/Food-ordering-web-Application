package com.tapfoods.dao;

import java.util.ArrayList;
import java.util.List;

import com.tapfoods.model.Menu;

public interface MenuDAO {
	
	String addMenu(Menu m);
	ArrayList<Menu> getAllMenu();
	List<Menu> getMenuByRestaurantId(int restaurantId);
	int updateMenuItem(int id);
	int deleteMenuItem(int id);
	
	
	 Menu getMenu(int menuId);
	

}
