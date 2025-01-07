package com.tapfoods.dao;

import com.tapfoods.model.User;

import java.util.ArrayList;

public interface UserDAO {
	
    int addUser(User user);
    
    ArrayList<User> getAllUsers();
    
    User getUserByEmail(String email);
    
    int updateUser(User user);
    
    int deleteUser(String email);
    
    User getUserByUsername(String username);
}
