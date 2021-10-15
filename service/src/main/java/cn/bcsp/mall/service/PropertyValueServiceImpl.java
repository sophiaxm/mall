package cn.bcsp.mall.service;

import cn.bcsp.mall.mapper.PropertyValueMapper;
import cn.bcsp.mall.pojo.PropertyValue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 参数值的业务层
 * @author: 王跃
 * @date: 2021年09月03日 15:44
 */
@Service("propertyValueService")
public class PropertyValueServiceImpl implements PropertyValueService {

    @Resource
    private PropertyValueMapper propertyValueMapper;

    @Override
    public List<PropertyValue> getAll(int productId) {
        return propertyValueMapper.selectAll(productId);
    }
}
