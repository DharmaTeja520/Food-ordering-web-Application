package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfoods.dao.UserDAO;
import com.tapfoods.dbUtils.DBUtils;
import com.tapfoods.model.User;

public class UserDAOImpl implements UserDAO {

    private Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;

    // SQL Queries
    private static final String ADD_USER = "INSERT INTO `user`(`username`, `password`, `email`, `address`, `role`, `createdDate`, `lastlogindate`) VALUES (?, ?, ?, ?, ?, NOW(), NOW())";
    private static final String SELECT_ALL = "SELECT * FROM `user`";
    private static final String SELECT_BY_EMAIL = "SELECT * FROM `user` WHERE `email` = ?";
    private static final String UPDATE_BY_EMAIL = "UPDATE `user` SET `username` = ?, `password` = ?, `address` = ?, `role` = ?, `lastlogindate` = NOW() WHERE `email` = ?";
    private static final String DELETE_BY_EMAIL = "DELETE FROM `user` WHERE `email` = ?";

    public UserDAOImpl() {
        try {
            con = DBUtils.myConnect(); // Establish database connection
            if (con == null) 
            {
                throw new SQLException("Database connection failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addUser(User user) {
        int status = 0;
        try {
            pstmt = con.prepareStatement(ADD_USER);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getRole());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt, null);
        }
        return status;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                userList.add(extractUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(stmt, resultSet);
        }
        return userList;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try {
            pstmt = con.prepareStatement(SELECT_BY_EMAIL);
            pstmt.setString(1, email);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                user = extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt, resultSet);
        }
        return user;
    }

    
    
    
    @Override
    public User getUserByUsername(String username) {
    	
    	String SELECT_BY_USERNAME = "SELECT * FROM `user` WHERE `username` = ?";
    	
        User user = null;
        
        try {
            pstmt = con.prepareStatement(SELECT_BY_USERNAME);
            pstmt.setString(1, username);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                user = extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt, resultSet);
        }
        return user;
    }

    
    
    
    
    @Override
    public int updateUser(User user) {
        int status = 0;
        try {
            pstmt = con.prepareStatement(UPDATE_BY_EMAIL);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getAddress());
            pstmt.setString(4, user.getRole());
            pstmt.setString(5, user.getEmail());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt, null);
        }
        return status;
    }

    @Override
    public int deleteUser(String email) {
        int status = 0;
        try {
            pstmt = con.prepareStatement(DELETE_BY_EMAIL);
            pstmt.setString(1, email);
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt, null);
        }
        return status;
    }

    // Helper method to extract a User object from a ResultSet
    private User extractUserFromResultSet(ResultSet res) throws SQLException {
        return new User(
                res.getInt("userId"),
                res.getString("username"),
                res.getString("email"),
                res.getString("password"),
                res.getString("address"),
                res.getString("role")
        );
    }

    // Utility method to close resources
    private void closeResources(AutoCloseable closeable, ResultSet res) {
        try {
            if (res != null) res.close();
            if (closeable != null) closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




//package com.tapfoods.daoimpl;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//
//import com.tapfoods.dao.UserDAO;
//import com.tapfoods.dbUtils.DBUtils;
//import com.tapfoods.model.User;
//
//public class UserDAOImpl implements UserDAO 
//{
//	Connection con;
//	private PreparedStatement pstmt;
//	private Statement stmt;
//	private ResultSet resultSet;
//	ArrayList<User> userList = new ArrayList<User>();
//	User user;
//
//	private static final String ADD_USER = "insert into `user`(`username`,`email`,`phoneNumber`,`password`,`address`) values (?,?,?,?,?)"; 
//	private static final String SELECT_ALL = "select * from `user`";
//	private static final String SELECT_ON_EMAIL = "select * from `user` where `email` = ?";
//	private static final String UPDATE_ON_EMAIL = "update `user` set `username`=?,`phoneNumber`=?,`password`=?,`address`=? where `email` = ?";
//	private static final String DELETE_ON_EMAIL = "delete  from `user` where `email` = ?";
//	
//	int status = 0;
//
//
//	public UserDAOImpl()
//	{
//		try
//		{
//			con = DBUtils.myConnect();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//
//
//
//	@Override
//	public int addUser(User u) 
//	{
//		try 
//		{
//			pstmt = con.prepareStatement(ADD_USER);
//			pstmt.setString(1, u.getUsername());
//			pstmt.setString(2, u.getEmail());
//			pstmt.setString(3, u.getRole());
//			pstmt.setString(4, u.getPassword());
//			pstmt.setString(5, u.getAddress());
//
//			status = pstmt.executeUpdate();
//		} 
//		catch (SQLException e) 
//		{
//			e.printStackTrace();
//		}
//		return status;
//	}
//
//
//
//
//
//	@Override
//	public ArrayList<User> getAllUsers() 
//	{
//		try
//		{
//			stmt = con.createStatement();
//			resultSet = stmt.executeQuery(SELECT_ALL);
//
//			userList = extractUserFromResultSet(resultSet);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		return userList;
//
//	}
//
//
//
//
//	public User getUser(String email) 
//	{
//		try
//		{
//			pstmt = con.prepareStatement(SELECT_ON_EMAIL);
//			pstmt.setString(1, email);
//
//			resultSet = pstmt.executeQuery();
//			userList = extractUserFromResultSet(resultSet);
//			user = userList.get(0);			// get 0th index of user details in userlist ref
//
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		return user;
//	}
//
//
//
//
//
//	ArrayList<User> extractUserFromResultSet(ResultSet resultSet)
//	{
//		try
//		{
//			while(resultSet.next())
//			{
//				userList.add(new User(
//						resultSet.getInt("userId"),
//						resultSet.getString("username"),
//						resultSet.getString("email"),
//						resultSet.getString("role"),
//						resultSet.getString("password"),
//						resultSet.getString("address")));
//
//			}
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		return userList;
//	}
//
//
//
//
//
//	@Override
//	public int updateUser(User u) 
//	{
//		try
//		{
//			pstmt = con.prepareStatement(UPDATE_ON_EMAIL);
//			pstmt.setString(1, u.getUsername());
//			pstmt.setString(2, u.getRole());
//			pstmt.setString(3, u.getPassword());
//			pstmt.setString(4, u.getAddress());
//			pstmt.setString(5, u.getEmail());
//
//			status = pstmt.executeUpdate();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		return status;
//	}
//
//
//
//	@Override
//	public int deleteUser(String email) 
//	{
//		try
//		{
//			pstmt = con.prepareStatement(DELETE_ON_EMAIL);
//			pstmt.setString(1, email);
//
//			status = pstmt.executeUpdate();		// status ->  no.of rows is affected
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		return status;
//	}
//
//
//
//
//
//	@Override
//	public User getUserByUsername(String username) {
//		
//		String SELECT_ON_USERNAME = "select * from `user` where `username` = ?";
//		User user = null;
//		
//		try 
//		{
//			pstmt = con.prepareStatement(SELECT_ON_USERNAME);
//			pstmt.setString(1, username);
//			resultSet = pstmt.executeQuery();
//			if(resultSet.next())
//			{
//				user = new User(
//						resultSet.getInt("userId"),
//						resultSet.getString("username"),
//						resultSet.getString("email"),
//						resultSet.getString("password"),
//						resultSet.getString("address"),
//						resultSet.getString("role"));
//						
//			}
//		} 
//		catch (SQLException e) 
//		{
//			e.printStackTrace();
//		}
//		
//		return user;
//		
//		
//
//	}
//
//
//
//
//	@Override
//	public User getUserByEmail(String email) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//}
