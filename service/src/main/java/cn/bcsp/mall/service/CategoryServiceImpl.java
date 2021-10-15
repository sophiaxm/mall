package cn.bcsp.mall.service;

import cn.bcsp.mall.mapper.CategoryMapper;
import cn.bcsp.mall.pojo.Category;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 商品分类的业务层
 * @author: 王跃
 * @date: 2021年08月31日 14:31
 */
@Service("categoryService")
public class CategoryServiceImpl implements  CategoryService {

    @Resource
    private CategoryMapper categoryMapper;


    @Override
    public List<Category> queryAll() {
        return categoryMapper.selectAll();
    }
}
