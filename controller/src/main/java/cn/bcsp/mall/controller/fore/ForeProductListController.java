package cn.bcsp.mall.controller.fore;

import cn.bcsp.mall.controller.BaseController;
import cn.bcsp.mall.pojo.Category;
import cn.bcsp.mall.pojo.Product;
import cn.bcsp.mall.pojo.ProductImage;
import cn.bcsp.mall.service.CategoryService;
import cn.bcsp.mall.service.ProductImageService;
import cn.bcsp.mall.service.ProductService;
import cn.bcsp.mall.service.ReviewService;
import cn.bcsp.mall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 前端商品搜索的控制器
 * @author:
 * @date: 2021年09月01日 14:45
 */
@Controller
public class ForeProductListController extends BaseController {
    @Resource
    private ProductService productService;
    @Resource
    private ProductImageService productImageService;
    @Resource
    private ReviewService reviewService;
    @Resource
    private CategoryService categoryService;
    @GetMapping(value = "/product")
    public String productList(@RequestParam(name = "productName",required = false) String productName,
                              HttpServletRequest request){
        //1 判断商品的名字是否为空,如果商品的名字为空,则直接跳转到首页
        if(productName==null||productName.trim().equals("")){
            return "redirect:/";
        }
        //2 根据商品的名字查询出商品的信息, 商品的预览图  商品的销售数   商品的评论数
        PageUtil pageUtil=new PageUtil(0,20);
        int pageStart=pageUtil.getPageStart();  //每页的开始条数
        int pageSize=pageUtil.getCount();  //每页显示的记录数
        List<Product> productList=
                productService.queryProductByName(productName,pageStart,pageSize);

        for(Product temp:productList){
            int pid=temp.getProductId(); //每个商品的ID
            //查询每个商品的预览图
            List<ProductImage> productImages=  productImageService.querySingleProductImages(pid);
            temp.setSingleProductImageList(productImages);
            //商品的成交数
            int saleCount= productService.saleCount(pid);
            temp.setProductSaleCount(saleCount);
            //商品的评论数
            int reviewCount=reviewService.queryCount(pid);
            temp.setProductReviewCount(reviewCount);
        }
        //3 根据商品的名字查询出商品的总记录数
        int totalCount=productService.queryTotalCountByName(productName);
        pageUtil.setTotal(totalCount);

        //4 显示前5条商品分类
        List<Category> list= categoryService.queryAll(); //查询所有商品分类
        //只显示前5条
        List<Category> categoryList;
        if(list==null||list.size()==0){
            categoryList=new ArrayList<>();
        }else if(list.size()>=5){
            categoryList= list.subList(0,5);
        }else{
            categoryList= list.subList(0,list.size());
        }

          request.setAttribute("categoryList",categoryList);
          request.setAttribute("productList",productList);
          request.setAttribute("searchValue",productName);
          request.setAttribute("pageUtil",pageUtil);
        return "fore/productListPage";
    }
}
