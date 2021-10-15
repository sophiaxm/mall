package cn.bcsp.mall.controller.fore;

import cn.bcsp.mall.pojo.Category;
import cn.bcsp.mall.pojo.Product;
import cn.bcsp.mall.pojo.ProductImage;
import cn.bcsp.mall.service.CategoryService;
import cn.bcsp.mall.service.ProductImageService;
import cn.bcsp.mall.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 首页控制器
 * @author: 王跃
 * @date: 2021年08月27日 15:06
 */
@Controller
public class HomePageController {
    @Resource
    private CategoryService categoryService;
    @Resource
    private ProductService productService;
    @Resource
    private ProductImageService productImageService;
    @RequestMapping(value = "/")
    public String homePage(Map<String,Object> map){
        //查询出所有的商品分类 select * from category
        List<Category> categoryList= categoryService.queryAll();
        for(int i=0;i<categoryList.size();i++){
            Category category=categoryList.get(i);
            //根据商品分类的ID查询该分类下的正则出售或者是促销的商品（显示前8条记录）
            // SELECT * FROM product WHERE productCategoryId=#{categoryId} AND productIsEnabled IN(0,2)  ORDER BY productId DESC  LIMIT 8
            List<Product> productList=
                    productService.queryProductByCategoryId(category.getCategoryId());
            for(Product productTemp:productList){
                int id=productTemp.getProductId();
                //根据商品的ID查询每个商品的一张(LIMIT 1)预览(productimageType=0)图片
                // SELECT * FROM productimage WHERE productimageProductId=#{id} AND productimageType=0  LIMIT 1
                ProductImage productImage= productImageService.querySingleProductImage(id);
                //设置每个商品的预览图片
                productTemp.getSingleProductImageList().add(productImage);
            }
            //设置每个分类下的商品信息
            category.setProductList(productList);
        }
        //查询6条促销商品信息
        //SELECT * FROM product WHERE productIsEnabled=2 LIMIT 6
        //SELECT * FROM product WHERE productIsEnabled=#{productIsEnabled} LIMIT #{size}
        List<Product> specialProductList=
                productService.queryProductByProductIsEnabled(2,6);
        map.put("categoryList",categoryList);
        map.put("specialProductList",specialProductList);
        return "fore/homePage";
    }
}
