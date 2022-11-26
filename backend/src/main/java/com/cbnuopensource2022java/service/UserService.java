package com.cbnuopensource2022java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbnuopensource2022java.entity.User;
import com.cbnuopensource2022java.repository.UserDao;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserByUserId(String userId) {
        return userDao.getUserByUserId(userId);
    }

    public User registerUser(User user) {
        return userDao.insertUser(user);
    }

    public void modifyUser(String userId, User user) {
        userDao.updateUser(userId, user);
    }

    public void removeUser(String userId) {
        userDao.deleteUser(userId);
    }
}
