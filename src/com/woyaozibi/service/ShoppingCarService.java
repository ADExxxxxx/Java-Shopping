package com.woyaozibi.service;

import com.woyaozibi.dao.ShoppingCarDao;
import com.woyaozibi.po.Products;
import com.woyaozibi.po.Shoppingcar;

import java.sql.SQLException;
import java.util.List;

public class ShoppingCarService {

    ShoppingCarDao shoppingCarDao = new ShoppingCarDao();

    // 为购物车添加商品
    public int addProduct(Products products) throws SQLException {
        return shoppingCarDao.AddProduct(products);
    }

    // 获取购物车列表
    public List<Shoppingcar> getShoppingCar() throws SQLException{
        return shoppingCarDao.getShoppingCar();
    }

    // 增加商品数量
    public int IncreaseProduct(int pid) throws  SQLException{
        return shoppingCarDao.IncreaseCount(pid);
    }

    // 减少商品数量
    public int DecreaseCount(int pid, int count) throws SQLException{
        return shoppingCarDao.DecreaseCount(pid, count);
    }

    //移除商品
    public int RemoveProduct(int pid) throws SQLException{
        return shoppingCarDao.RemoveProduct(pid);
    }

}
