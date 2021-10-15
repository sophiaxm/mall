package cn.bcsp.mall.mapper;

import cn.bcsp.mall.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    //查询商品分类信息
    List<Category> selectAll();


}