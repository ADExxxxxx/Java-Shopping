package com.woyaozibi.dao;

import com.woyaozibi.po.Users;
import com.woyaozibi.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {
    private QueryRunner queryRunner = null;

    // ����¼��Ϣ�Ƿ�����
    public Users checkUsers(String username, String password) throws SQLException{
        queryRunner = C3P0Utils.getQueryRunner();

        String sql = "select * from users where username=? and password=?";

        Users user = queryRunner.query(sql, new BeanHandler<Users>(Users.class), username, password);

        return user;
    }

    // ����Ƿ��Ѿ������û�
    public Users hasSameUser(String username) throws SQLException{
        queryRunner = C3P0Utils.getQueryRunner();

        String sql = "select * from users where username=?";

        Users user = queryRunner.query(sql, new BeanHandler<Users>(Users.class), username);

        return user;
    }

    // ����û�
    public int InsertUser(Users users) throws SQLException{
        int result = 0;
        queryRunner = C3P0Utils.getQueryRunner();
        if (hasSameUser(users.getUsername()) == null){
            String sql = "insert into users values(null,?,?,?,?,?)";
            result = queryRunner.update(sql, users.getUsername(),users.getPassword(),users.getName(),users.getEmail(), users.getPhone());
        }else{
            System.out.println("�Ѵ�����ͬ�˻�");
        }
        return result;
    }
}
