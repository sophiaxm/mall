package cn.bcsp.mall.mapper;

import cn.bcsp.mall.pojo.ProductImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductImageMapper {

    // 根据商品的ID查询商品的一张(limit 1)预览(productimageType=0)图片
    ProductImage selectSingleProductImage(@Param("id") int id);


    // 根据商品的ID查询商品的所有预览
    List<ProductImage> selectSingleProductImages(@Param("id") int id);


    //根据商品的ID查询商品的所有图片
    List<ProductImage> selectAll(@Param("id") int id);

}