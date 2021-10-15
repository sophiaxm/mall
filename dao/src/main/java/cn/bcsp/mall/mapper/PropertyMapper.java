package cn.bcsp.mall.mapper;

import cn.bcsp.mall.pojo.Property;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PropertyMapper {
    //根据商品的分类的ID,查询商品的参数集合
    List<Property> selectAll(@Param("categoryId") int categoryId);
}