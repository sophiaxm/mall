package cn.bcsp.mall.service;

import cn.bcsp.mall.pojo.ProductOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductOrderItemService {
    //查询购物车中的信息
    List<ProductOrderItem> getListByUserId(int userId);

    //根据用户的ID 商品ID更新对应的购物车中的商品数量
    int updateProductOrderItem(int userId,int productId,int productNumber);

    //向购物车中添加新的商品
    int save(ProductOrderItem productOrderItem);

    //删除购物车中的信息
    int delete( int productOrderItemId );
}