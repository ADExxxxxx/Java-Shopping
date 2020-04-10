package com.woyaozibi.dao;

import com.woyaozibi.po.Products;
import com.woyaozibi.po.Shoppingcar;
import com.woyaozibi.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ShoppingCarDao {
    private QueryRunner queryRunner = C3P0Utils.getQueryRunner();

    // ��⹺�ﳵ�Ƿ����и���Ʒ
    public Products hasProduct(int pid) throws SQLException{
        String sql = "select * from shoppingcar where pid=?";
        Products products = queryRunner.query(sql, new BeanHandler<Products>(Products.class), pid);
        return products;
    }

    // Ϊ���ﳵ�����Ʒ
    public int AddProduct(Products products) throws SQLException {
        int result = 0;
        if (hasProduct(products.getPid()) == null){
            String sql = "insert into shoppingcar values(null,?,?,?,?,?,?)";
            result = queryRunner.update(sql,products.getPid(), products.getPname(), products.getPrice(), 1, products.getImgurl(), 1);
        }else{
            String sql = "update shoppingcar set count=count+1 where pid=?";
            result = queryRunner.update(sql,products.getPid());
        }
        return result;
    }

    // ��ȡ���й��ﳵ��Ʒ
    public List<Shoppingcar> getShoppingCar() throws SQLException {
        String sql = "select * from shoppingcar";
        List<Shoppingcar> shoppingcars = queryRunner.query(sql,new BeanListHandler<Shoppingcar>(Shoppingcar.class));
        return shoppingcars;
    }

    // ������Ʒ����
    public int IncreaseCount(int pid) throws SQLException{
        int result = 0;
        String sql = "update shoppingcar set count=count+1 where pid=?";
        result = queryRunner.update(sql, pid);
        return result;
    }

    // ������Ʒ����
    public int DecreaseCount(int pid, int count) throws SQLException{
        int result = 0;
        if (count >= 1) {
            String sql = "update shoppingcar set count=count-1 where pid=?";
            result = queryRunner.update(sql, pid);
        }else {
            result = RemoveProduct(pid);
        }
        return result;
    }

    //�Ƴ���Ʒ
    public int RemoveProduct(int pid) throws  SQLException{
        int result = 0;

        String sql = "update shoppingcar set count=0 where pid=?";
        result = queryRunner.update(sql, pid);

        return result;
    }

}
