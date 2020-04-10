package com.woyaozibi.service;

import com.woyaozibi.dao.UserDao;
import com.woyaozibi.po.Users;

import java.sql.SQLException;

public class UserService {
    private UserDao userDao = new UserDao();

    // ����¼��Ϣ�Ƿ�����
    public Users checkUsers(String username, String password) throws SQLException {
        return userDao.checkUsers(username, password);
    }

    // �������û�
    public int InsertUser(Users users) throws SQLException{
        return userDao.InsertUser(users);
    }

}
