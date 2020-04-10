package com.woyaozibi.dao;

import com.woyaozibi.po.Products;
import com.woyaozibi.po.Shoppingcar;
import com.woyaozibi.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductsDao {

    private QueryRunner queryRunner = null;

    // 获取商品列表
    public List<Products> getProducts() throws SQLException {


        queryRunner = C3P0Utils.getQueryRunner();

        String sql = "select * from products";

        List<Products> productsList = queryRunner.query(sql,new BeanListHandler<Products>(Products.class));


        return productsList;
    }

    // 获取单个商品信息
    public Products getProductInfo(int id) throws SQLException{

        queryRunner = C3P0Utils.getQueryRunner();

        String sql = "select * from products where pid=?";

        Products productsInfo = queryRunner.query(sql, new BeanHandler<Products>(Products.class), id);

        return productsInfo;

    }

}


