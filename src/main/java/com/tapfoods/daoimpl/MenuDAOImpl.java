package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tapfoods.dao.MenuDAO;
import com.tapfoods.model.Menu;

public class MenuDAOImpl implements MenuDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/online_food_delivery"; // Update with your database connection
    private static final String USERNAME = "root"; // Update with your database username
    private static final String PASSWORD = "Dharma"; // Update with your database password

    // Helper method to extract Menu objects from ResultSet
    private List<Menu> extractAllFromResultSet(ResultSet res) throws SQLException {
        List<Menu> menuList = new ArrayList<>();
        while (res.next()) {
            Menu menu = new Menu();
            menu.setMenuId(res.getInt("menuId"));
            menu.setRestaurantId(res.getInt("restaurantId"));
            menu.setItemName(res.getString("itemName"));
            menu.setDescription(res.getString("description"));
            menu.setPrice( res.getFloat("price"));
            menu.setImgpath(res.getString("imgPath"));
            menuList.add(menu);
        }
        return menuList;
    }

    // Method to add a new menu item
    @Override
    public String addMenu(Menu m) {
        String query = "INSERT INTO menu (restaurantId, itemName, description, price, imgPath) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, m.getRestaurantId());
            pstmt.setString(2, m.getItemName());
            pstmt.setString(3, m.getDescription());
            pstmt.setDouble(4, m.getPrice());
            pstmt.setString(5, m.getImgpath());

            int result = pstmt.executeUpdate();
            if (result > 0) {
                return "Menu item added successfully!";
            } else {
                return "Failed to add menu item.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error while adding menu item: " + e.getMessage();
        }
    }

    // Method to get all menu items
    @Override
    public ArrayList<Menu> getAllMenu() {
        String query = "SELECT * FROM menu";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet res = stmt.executeQuery(query)) {

            return new ArrayList<>(extractAllFromResultSet(res));
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Method to get the menu items by restaurant ID
    @Override
    public List<Menu> getMenuByRestaurantId(int restaurantId) {
        String query = "SELECT * FROM menu WHERE restaurantId = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, restaurantId);
            ResultSet res = pstmt.executeQuery();

            List<Menu> menuList = extractAllFromResultSet(res);
            if (menuList.isEmpty()) 
            {
                return null; // No menu found for the given restaurant ID
            } 
            else 
            {
                return menuList; // Assuming we only want the first item for the given restaurant ID
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    // Method to update a menu item
    @Override
    public int updateMenuItem(int id) {
        String query = "UPDATE menu SET itemName = ?, description = ?, price = ?, imgPath = ? WHERE menuId = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, "Updated Name"); // Example updated name
            pstmt.setString(2, "Updated Description"); // Example updated description
            pstmt.setDouble(3, 10.9); // Example price
            pstmt.setString(4, null); // 
            pstmt.setInt(5, id);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Update failed
        }
    }

    // Method to delete a menu item
    @Override
    public int deleteMenuItem(int id) {
        String query = "DELETE FROM menu WHERE menuId = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Delete failed
        }
    }

    
    
 // Method to get a specific menu item by menuId
	@Override
	 public Menu getMenu(int menuId) {
        String query = "SELECT * FROM menu WHERE menuId = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, menuId);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Menu menu = new Menu();
                menu.setMenuId(res.getInt("menuId"));
                menu.setRestaurantId(res.getInt("restaurantId"));
                menu.setItemName(res.getString("itemName"));
                menu.setDescription(res.getString("description"));
                menu.setPrice(res.getFloat("price"));
                menu.setImgpath(res.getString("imgPath"));
                return menu;
            } else {
                return null; // No menu found for the given menu ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
