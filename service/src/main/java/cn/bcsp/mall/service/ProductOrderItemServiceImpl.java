package cn.bcsp.mall.service;

import cn.bcsp.mall.mapper.ProductOrderItemMapper;
import cn.bcsp.mall.pojo.ProductOrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: TODO
 * @author: 王跃
 * @date: 2021年09月06日 15:33
 */
@Service("productOrderItemService")
public class ProductOrderItemServiceImpl implements  ProductOrderItemService{

    @Resource
    private ProductOrderItemMapper productOrderItemMapper;

    //查询购物车中的信息
    public List<ProductOrderItem> getListByUserId(int userId) {
        return productOrderItemMapper.selectAllByUserId(userId);
    }

    @Transactional
    public int updateProductOrderItem(int userId, int productId, int productNumber) {
        return productOrderItemMapper.updateProductOrderItem(userId,productId,productNumber);
    }

    @Transactional
    public int save(ProductOrderItem productOrderItem) {
        return productOrderItemMapper.insert(productOrderItem);
    }

    @Transactional
    public int delete(int productOrderItemId) {
        return productOrderItemMapper.delete(productOrderItemId);
    }
}
