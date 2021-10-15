package cn.bcsp.mall.service;

import cn.bcsp.mall.mapper.ProductImageMapper;
import cn.bcsp.mall.pojo.ProductImage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 商品图片的业务层
 * @author: 王跃
 * @date: 2021年08月31日 15:35
 */
@Service("productImageService")
public class ProductImageServiceImpl implements  ProductImageService {


    @Resource
    private ProductImageMapper productImageMapper;


    @Override
    public ProductImage querySingleProductImage(int id) {
        return productImageMapper.selectSingleProductImage(id);
    }

    @Override
    public List<ProductImage> querySingleProductImages(int id) {
        return productImageMapper.selectSingleProductImages(id);
    }

    @Override
    public List<ProductImage> queryAll(int id) {
        return productImageMapper.selectAll(id);
    }
}
