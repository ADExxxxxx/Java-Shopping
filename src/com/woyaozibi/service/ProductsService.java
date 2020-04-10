package com.woyaozibi.service;

import com.woyaozibi.dao.ProductsDao;
import com.woyaozibi.po.Products;
import com.woyaozibi.po.Shoppingcar;

import java.sql.SQLException;
import java.util.List;

public class ProductsService {

    ProductsDao productsDao = new ProductsDao();

    // ��ȡ������Ʒ
    public List<Products> getProducts() throws SQLException {
        return productsDao.getProducts();
    }

    // ��ȡ��Ʒ��Ϣ
    public Products getProductInfo(int id) throws  SQLException{
        return productsDao.getProductInfo(id);
    }


}
