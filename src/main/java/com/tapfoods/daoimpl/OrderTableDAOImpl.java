package com.tapfoods.daoimpl;

import com.tapfoods.dao.OrderTableDAO;
import com.tapfoods.dbUtils.DBUtils;
import com.tapfoods.model.Menu;
import com.tapfoods.model.OrderTable;

import java.sql.*;
import java.util.ArrayList;

public class OrderTableDAOImpl implements OrderTableDAO {
    private Connection con;

    public OrderTableDAOImpl() {
        con = DBUtils.myConnect();
    }

    @Override
    public String addOrder(OrderTable o) {
        // Implementation goes here...
    }

    @Override
    public ArrayList<Menu> getAllOrders(int userId) {
        // Implementation goes here...
    }

    @Override
    public Menu getOrder(int userId) {
        // Implementation goes here...
    }
}
