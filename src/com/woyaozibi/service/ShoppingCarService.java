package com.woyaozibi.service;

import com.woyaozibi.dao.ShoppingCarDao;
import com.woyaozibi.po.Products;
import com.woyaozibi.po.Shoppingcar;

import java.sql.SQLException;
import java.util.List;

public class ShoppingCarService {

    ShoppingCarDao shoppingCarDao = new ShoppingCarDao();

    // Ϊ���ﳵ�����Ʒ
    public int addProduct(Products products) throws SQLException {
        return shoppingCarDao.AddProduct(products);
    }

    // ��ȡ���ﳵ�б�
    public List<Shoppingcar> getShoppingCar() throws SQLException{
        return shoppingCarDao.getShoppingCar();
    }

    // ������Ʒ����
    public int IncreaseProduct(int pid) throws  SQLException{
        return shoppingCarDao.IncreaseCount(pid);
    }

    // ������Ʒ����
    public int DecreaseCount(int pid, int count) throws SQLException{
        return shoppingCarDao.DecreaseCount(pid, count);
    }

    //�Ƴ���Ʒ
    public int RemoveProduct(int pid) throws SQLException{
        return shoppingCarDao.RemoveProduct(pid);
    }

}
