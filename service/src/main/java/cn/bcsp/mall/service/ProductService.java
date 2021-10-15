package cn.bcsp.mall.service;

import cn.bcsp.mall.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductService {

    List<Product> queryProductByCategoryId(int categorId);

    List<Product> queryProductByProductIsEnabled(int productIsEnabled,int size);

    List<Product> queryProductByName(String productName,int startRow,int pageSize);

    int saleCount(int productId);

    int queryTotalCountByName(String productName);

    Product getOne(int productId);

    int queryCount(int categoryId);

    List<Product> queryProductByCategoryId2( int categoryId,int startRow,int pageSize);

}