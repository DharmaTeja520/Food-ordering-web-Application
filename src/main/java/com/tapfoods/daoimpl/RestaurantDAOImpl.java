package com.tapfoods.daoimpl;

import com.tapfoods.dao.RestaurantDAO;
import com.tapfoods.dbUtils.DBUtils;
import com.tapfoods.model.Restaurant;

import java.sql.*;
import java.util.ArrayList;

public class RestaurantDAOImpl implements RestaurantDAO {

    private static final String ADD_RESTAURANT = "INSERT INTO restaurant (restaurantName, cuisineType, deliveryTime, address, adminUserId, rating, isActive, imgPath) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM restaurant";
    private static final String SELECT_BY_ID = "SELECT * FROM restaurant WHERE restaurantId = ?";
    private static final String UPDATE_RESTAURANT = "UPDATE restaurant SET restaurantName = ?, cuisineType = ?, deliveryTime = ?, address = ?, adminUserId = ?, rating = ?, isActive = ?, imgPath = ? WHERE restaurantId = ?";
    private static final String DELETE_BY_NAME = "DELETE FROM restaurant WHERE restaurantName = ?";

    private Connection con;

    public RestaurantDAOImpl() {
        con = DBUtils.myConnect();
        if (con == null) {
            throw new RuntimeException("Database connection failed.");
        }
    }

    @Override
    public int addRestaurant(Restaurant r) {
        int status = 0;
        try (PreparedStatement pstmt = con.prepareStatement(ADD_RESTAURANT)) {
            pstmt.setString(1, r.getRestaurantName());
            pstmt.setString(2, r.getCuisineType());
            pstmt.setInt(3, r.getDeliveryTime());
            pstmt.setString(4, r.getAddress());
            pstmt.setInt(5, r.getAdminUserId());
            pstmt.setDouble(6, r.getRating());
            pstmt.setBoolean(7, r.isActive());
            pstmt.setString(8, r.getImgPath());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public ArrayList<Restaurant> getAllRestaurants() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet res = stmt.executeQuery(SELECT_ALL)) {
            while (res.next()) 
            {
                restaurants.add(extractRestaurantFromResultSet(res));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    @Override
    public Restaurant getRestaurant(int restaurantId) {
        Restaurant restaurant = null;
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID)) {
            pstmt.setInt(1, restaurantId);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                restaurant = extractRestaurantFromResultSet(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public Restaurant updateRestaurant(Restaurant r) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_RESTAURANT)) {
            pstmt.setString(1, r.getRestaurantName());
            pstmt.setString(2, r.getCuisineType());
            pstmt.setInt(3, r.getDeliveryTime());
            pstmt.setString(4, r.getAddress());
            pstmt.setInt(5, r.getAdminUserId());
            pstmt.setDouble(6, r.getRating());
            pstmt.setBoolean(7, r.isActive());
            pstmt.setString(8, r.getImgPath());
            pstmt.setInt(9, r.getRestaurantId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public int deleteRestaurant(String name) {
        int status = 0;
        try (PreparedStatement pstmt = con.prepareStatement(DELETE_BY_NAME)) {
            pstmt.setString(1, name);
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    // Helper method to extract a Restaurant object from a ResultSet
    private Restaurant extractRestaurantFromResultSet(ResultSet res) throws SQLException {
        return new Restaurant(
                res.getInt("restaurantId"),
                res.getString("restaurantName"),
                res.getString("cuisineType"),
                res.getInt("deliveryTime"),
                res.getString("address"),
                res.getInt("adminUserId"),
                res.getDouble("rating"),
                res.getBoolean("isActive"),
                res.getString("imgPath")
        );
    }
}
