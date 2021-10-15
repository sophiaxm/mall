package cn.bcsp.mall.mapper;

import cn.bcsp.mall.pojo.ProductOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductOrderItemMapper {

    //根据用户的ID查询用户的购物车信息
    List<ProductOrderItem> selectAllByUserId(@Param("userId") int userId);


    //根据用户的ID 商品ID更新对应的购物车中的商品数量
    int updateProductOrderItem(@Param("userId") int userId,
                               @Param("productId") int productId,
                               @Param("productNumber")int productNumber);

    //新增
    int insert(ProductOrderItem productOrderItem);


    //删除购物车中的信息
    int delete(@Param("productOrderItemId") int productOrderItemId );
}