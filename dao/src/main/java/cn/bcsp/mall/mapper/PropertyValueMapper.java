package cn.bcsp.mall.mapper;

import cn.bcsp.mall.pojo.PropertyValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PropertyValueMapper {

    List<PropertyValue> selectAll(@Param("productId") int productId);
}