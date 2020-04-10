package com.woyaozibi.service;

import com.woyaozibi.dao.UserDao;
import com.woyaozibi.po.Users;

import java.sql.SQLException;

public class UserService {
    private UserDao userDao = new UserDao();

    // 检查登录信息是否有误
    public Users checkUsers(String username, String password) throws SQLException {
        return userDao.checkUsers(username, password);
    }

    // 插入新用户
    public int InsertUser(Users users) throws SQLException{
        return userDao.InsertUser(users);
    }

}
