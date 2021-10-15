package cn.bcsp.mall.service;

import cn.bcsp.mall.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {
    //根据商品的ID查询商品的一张预览图片
    ProductImage querySingleProductImage(int id);

    //根据商品的ID查询商品的预览图片
    List<ProductImage> querySingleProductImages(int id);

    //根据商品的ID查询商品的所有图片
    List<ProductImage> queryAll(int id);
}