package cn.bcsp.mall.service;

import cn.bcsp.mall.mapper.ProductMapper;
import cn.bcsp.mall.pojo.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 商品的业务层
 * @author: 王跃
 * @date: 2021年08月31日 15:05
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;


    @Override
    public int queryCount(int categoryId) {
        return productMapper.selectCount(categoryId);
    }

    @Override
    public List<Product> queryProductByCategoryId2(int categoryId, int startRow, int pageSize) {
        return productMapper.selectProductByCategoryId2(categoryId,startRow,pageSize);
    }

    @Override
    public Product getOne(int productId) {
        return productMapper.selectOne(productId);
    }

    @Override
    public int queryTotalCountByName(String productName) {
        return productMapper.count(productName);
    }

    //根据商品的ID查询商品的成交数量
    public int saleCount(int productId) {
        return productMapper.saleCount(productId);
    }

    @Override
    public List<Product> queryProductByName(String productName, int startRow, int pageSize) {
        return productMapper.selectProductByName(productName,startRow,pageSize);
    }

    @Override
    public List<Product> queryProductByCategoryId(int categorId) {
        return productMapper.selectProductByCategoryId(categorId);
    }

    @Override
    public List<Product> queryProductByProductIsEnabled(int productIsEnabled, int size)
    {
        return productMapper.selectProductByProductIsEnabled(productIsEnabled,size);
    }

}
