package cn.bcsp.mall.service;

import cn.bcsp.mall.pojo.PropertyValue;

import java.util.List;

/**
 * @Description: TODO
 * @author: 王跃
 * @date: 2021年09月03日 15:42
 */
public interface PropertyValueService {
    //根据商品的ID查询商品对应的属性值的集合
    List<PropertyValue> getAll(int productId);
}
