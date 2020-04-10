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

    // 检测购物车是否已有该商品
    public Products hasProduct(int pid) throws SQLException{
        String sql = "select * from shoppingcar where pid=?";
        Products products = queryRunner.query(sql, new BeanHandler<Products>(Products.class), pid);
        return products;
    }

    // 为购物车添加商品
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

    // 获取所有购物车商品
    public List<Shoppingcar> getShoppingCar() throws SQLException {
        String sql = "select * from shoppingcar";
        List<Shoppingcar> shoppingcars = queryRunner.query(sql,new BeanListHandler<Shoppingcar>(Shoppingcar.class));
        return shoppingcars;
    }

    // 增加商品数量
    public int IncreaseCount(int pid) throws SQLException{
        int result = 0;
        String sql = "update shoppingcar set count=count+1 where pid=?";
        result = queryRunner.update(sql, pid);
        return result;
    }

    // 减少商品数量
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

    //移除商品
    public int RemoveProduct(int pid) throws  SQLException{
        int result = 0;

        String sql = "update shoppingcar set count=0 where pid=?";
        result = queryRunner.update(sql, pid);

        return result;
    }

}
