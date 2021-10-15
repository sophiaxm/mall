package cn.bcsp.mall.service;

import cn.bcsp.mall.mapper.PropertyMapper;
import cn.bcsp.mall.pojo.Property;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 商品参数的业务层
 * @author: 王跃
 * @date: 2021年09月03日 15:20
 */
@Service("propertyService")
public class PropertyServiceImpl implements  PropertyService{
    @Resource
    private PropertyMapper propertyMapper;

    @Override
    public List<Property> getAll(int categoryId) {
       return   propertyMapper.selectAll(categoryId);
    }
}
