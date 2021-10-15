package cn.bcsp.mall.controller.fore;

import cn.bcsp.mall.controller.BaseController;
import cn.bcsp.mall.pojo.*;
import cn.bcsp.mall.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Description: 商品的详细信息
 * @author:
 * @date: 2021年09月02日 16:57
 */
@Controller
public class ForeProductDetailController extends BaseController {
    @Resource
    private ProductService  productService;
    @Resource
    private ReviewService reviewService;
    @Resource
    private ProductImageService productImageService;
    @Resource
    private PropertyService propertyService;
    @Resource
    private PropertyValueService propertyValueService;
    @Resource
    private CategoryService categoryService;
    @RequestMapping("/product/{id}")
    public String detail(@PathVariable("id") int productId, HttpServletRequest request){
        //1 根据商品的ID查询商品的信息
        //商品的信息,验证商品是否为空,如果商品为空 则跳转到错误页面
        //判断下商品的状态,如果商品的状态是下架状态则跳转到错误页面
        // select * from product where productId=#{productId}
        Product product= productService.getOne(productId);
        if(product==null||product.getProductIsEnabled()==1){
            return "fore/errorPage";
        }

        //2 根据商品ID获得此商品的所有图片: 预览图片 + 详情图片
        // SELECT * FROM productimage WHERE  productimageProductId=#{id}
        List<ProductImage> list= productImageService.queryAll(productId);
        List<ProductImage> singleProductImageList=new ArrayList<>();//预览图片
        List<ProductImage> detailProductImageList=new ArrayList<>(); //详情图片
        for(int i=0;i<list.size();i++){  //判断图片是预览图还是详情图
            ProductImage productImage=  list.get(i);
            if(productImage.getProductImageType()==0){
                singleProductImageList.add(productImage);
            }else{
                detailProductImageList.add(productImage);
            }
        }
        product.setSingleProductImageList(singleProductImageList); //预览图片
        product.setDetailProductImageList(detailProductImageList); //详情图片

        //3 销售的总数 以前的接口
        // SELECT SUM(productorderitemNumber)
        //              FROM productorderitem WHERE productorderitemproductId=#{pid}
        int saleCount=productService.saleCount(productId);
        //4 商品的评论数 以前的接口
        //  SELECT COUNT(reviewId) FROM review WHERE reviewProductId=#{productId}
        int reviewCount= reviewService.queryCount(productId);
        product.setProductSaleCount(saleCount);
        product.setProductReviewCount(reviewCount);

        //获得商品的分类ID
        int categoryId=product.getProductCategoryId();
        //5 根据的商品分类ID获得对应的参数
        //property(参数表): 参数的ID  参数的名字   商品的分类ID
        // select * from property where propertyCategoryId=#{categoryId}
        List<Property> propertyList= propertyService.getAll(categoryId);

        //根据商品的ID获得对应的参数值
        //6 select * from propertyvalue where propertyValueProductId=#{productId}
        //propertyvalue(参数值表)表中字段: 参数值的ID  参数值   参数的ID   商品的ID
       List<PropertyValue> propertyValueList= propertyValueService.getAll(productId);

       //7 进行匹配,参数值放到对应的参数中
        for(Property property:propertyList){
            List<PropertyValue> list1=new ArrayList<>();
            //获得参数列表中的每一个参数
            for(PropertyValue propertyValue:propertyValueList){
                if(property.getPropertyId()==propertyValue.getPropertyValuePropertyId()){
                    list1.add(propertyValue);
                }
            }
            property.setPropertyValueList(list1);
        }

        //8 商品的分类信息  //select * from category
        List<Category> tempList= categoryService.queryAll();
        List<Category> categoryList=null;
        if(tempList==null||tempList.size()==0){
            categoryList=new ArrayList<>();
        }else if(tempList.size()>3){
            categoryList=tempList.subList(0,3);
        }else{
            categoryList=tempList.subList(0,tempList.size());
        }
        //9 猜你喜欢 （当前正在查看的商品同类型的商品信息,随机推荐3个）
        //select count(*) from product where productCategoryId=#{categoryId}
        int total=productService.queryCount(categoryId); //总共有9条
        //每页的开始条数
        int index=new Random().nextInt(total);
        if(index+3>total){    //如果当前页取不满3条记录
            index=total-3;    //取满3条记录
        }
        if(index<0){
            index=0;
        }
        //随机的获得本类型的3条商品信息
        //  SELECT * FROM product WHERE productCategoryId=#{categoryId} AND
        //           productIsEnabled IN(0,2)
        //           ORDER BY productId DESC  LIMIT  #{startRow},#{pageSize}

       List<Product> loveProductList=
               productService.queryProductByCategoryId2(categoryId,index,3);
       if(loveProductList!=null){
           //10 猜你喜欢的商品 设置预览图  以前的接口
           for(Product tempProduct:loveProductList){
              ProductImage productImage=
                      productImageService.querySingleProductImage(tempProduct.getProductId());
              tempProduct.getSingleProductImageList().add(productImage);
           }
       }
        request.setAttribute("product",product);
        request.setAttribute("propertyList",propertyList);
        request.setAttribute("categoryList",categoryList);
        request.setAttribute("loveProductList",loveProductList);
        return "fore/productDetailsPage";
    }

}











