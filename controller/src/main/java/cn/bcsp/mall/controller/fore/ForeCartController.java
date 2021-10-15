package cn.bcsp.mall.controller.fore;

import cn.bcsp.mall.controller.BaseController;
import cn.bcsp.mall.pojo.Product;
import cn.bcsp.mall.pojo.ProductImage;
import cn.bcsp.mall.pojo.ProductOrderItem;
import cn.bcsp.mall.pojo.User;
import cn.bcsp.mall.service.ProductImageService;
import cn.bcsp.mall.service.ProductOrderItemService;
import cn.bcsp.mall.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 购物车的控制器
 * @author:
 * @date: 2021年09月08日 14:20
 */
@Controller
public class ForeCartController extends BaseController {
    @Resource
    private ProductOrderItemService productOrderItemService;
    @Resource
    private ProductService productService;
    @Resource
    private ProductImageService productImageService;
    @RequestMapping(value = "cart", method = RequestMethod.GET)
    public String goToCartPage(Map<String, Object> map, HttpSession session) {
        User user= getLoginUser(session); //1 判断用户是否登录,如果用户没有登录调整到登录页面
        if(user==null){
            return "forward:/toLogin";
        }
        int userId= user.getUserId();
        List<ProductOrderItem> productOrderItemList=
                productOrderItemService.getListByUserId(userId);
        int orderItemTotal=0; //默认此用户购买了0件商品
        //3 如果用户登录了,查询此用户的购物车中的商品信息,如果商品信息不为空,查下商品的信息以及预览图
        if(productOrderItemList.size()>0){
            for(ProductOrderItem temp:productOrderItemList){
                //orderItemTotal+=temp.getProductOrderItemNumber();   //对购物车中的商品数量进行累加
                orderItemTotal++;
                int productId= temp.getProductOrderItemProductId();
                Product product= productService.getOne(productId);//根据购物车中的商品的ID 查询商品信息
                ProductImage productImage=
                        productImageService.querySingleProductImage(productId); //根据购物车中的商品的ID 查询商品预览图
                product.getSingleProductImageList().add(productImage); //将预览图封装到商品中
                temp.setProductOrderItemProduct(product);
            }
        }
        map.put("orderItemList",productOrderItemList);
        map.put("orderItemTotal",orderItemTotal);
        return "fore/productBuyCarPage";
    }

    @RequestMapping(value = "/orderItem/{orderItemId}",
             method = RequestMethod.DELETE,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object>
                 deleteOrderItem(@PathVariable(value = "orderItemId") int orderItemId,
                                 HttpSession session){
        //orderItemId 要删除的购物车中的记录的ID
        Map<String,Object> map=new HashMap<>();
        User user= getLoginUser(session);
        if(user==null){  //1 判断用户是否登录,如果用户没有登录调整到登录页面
            map.put("success",false);
            map.put("href","/toLogin");
            return map;
        }
        int userId=user.getUserId();  //用户的ID
        List<ProductOrderItem> productOrderItemList=
                productOrderItemService.getListByUserId(userId);  //根据用户的ID获得购物车中的商品信息
        boolean isMine=false;  //要删除的商品是否在购物车中
        for(ProductOrderItem temp:productOrderItemList){  //遍历购物车中的商品信息
            if(temp.getProductOrderItemId()==orderItemId){  //购物车中的productOrderItem的ID等于要删除的orderItemID
                isMine=true;  //要删除的商品是在购物车中
            }
        }
        int result=0;
        if(isMine) {
             result = productOrderItemService.delete(orderItemId);  //根据购物车中的记录的IDorderItemId 删除购物车中的记录
        }
        if(result>0){
            map.put("success",true);
        }else{
            map.put("success",false);
        }
        map.put("href","/cart");
        return map;
    }
}
