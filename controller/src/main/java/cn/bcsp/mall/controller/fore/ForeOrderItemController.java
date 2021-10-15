package cn.bcsp.mall.controller.fore;

import cn.bcsp.mall.controller.BaseController;
import cn.bcsp.mall.pojo.Address;
import cn.bcsp.mall.pojo.Product;
import cn.bcsp.mall.pojo.ProductOrderItem;
import cn.bcsp.mall.pojo.User;
import cn.bcsp.mall.service.AddressService;
import cn.bcsp.mall.service.ProductOrderItemService;
import cn.bcsp.mall.service.ProductService;
import cn.bcsp.mall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 购物车和订单的控制器
 * @author:
 * @date: 2021年09月06日 14:30
 */
@Controller
public class ForeOrderItemController extends BaseController {
    @Resource
    private ProductOrderItemService productOrderItemService;
    @Resource
    private ProductService productService;

    @Resource
    private AddressService addressService;

    //将商品加入到购物车中
    @PostMapping(value = "/orderItem/create/{productId}",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String, Object> createOrderItem(@PathVariable("productId") Integer productId,
                                               @RequestParam(name = "product_number", defaultValue = "1") Short productNumber,
                                               HttpSession session,
                                               HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //获得登录的用户信息
        User user = getLoginUser(session);
        if (user == null) {
            map.put("success", false);
            map.put("url", "/toLogin");
            return map;
        }
        //根据商品的ID查询商品信息,如果没有找到商品,此商品ID不正确的
        Product product = productService.getOne(productId);
        if (product == null) {
            map.put("success", false);
            map.put("url", "/toLogin");
            return map;
        }
        //1 购物车中是否已经有该商品 询出当前登录的用户他的购物车中的商品信息
        // SELECT * FROM productorderitem WHERE productorderitemOrderId IS NULL AND productorderitemuserId=#{userId}
        int userId = user.getUserId();
        List<ProductOrderItem> productOrderItemList =
                productOrderItemService.getListByUserId(userId);
        //接着在去判断向购物车中添加的商品是否已经存在
        for (ProductOrderItem temp : productOrderItemList) {
            //2 如果已经有该商品,则更新购物车中的商品数量
            if (temp.getProductOrderItemProductId().equals(productId)) {
                //在原有的数量的基础上添加商品的数量(productNumber)
                // UPDATE productorderitem SET productorderitemNumber = productorderitemNumber + #{productNumber}
                //         WHERE productorderitemProductId=#{productId}
                //         AND  productorderitemuserId=#{userId}
                //         AND productorderitemOrderId IS NULL
                int result = productOrderItemService.updateProductOrderItem(userId, productId, productNumber);
                if (result > 0) {
                    map.put("success", true);
                } else {
                    map.put("success", false);
                }
                return map;
            }
        }
        //3 如果购物车中没有该商品,商品新增到购物车中
        ProductOrderItem productOrderItem = new ProductOrderItem();
        productOrderItem.setProductOrderItemNumber(productNumber);
        double price = product.getProductPrice() * productNumber; //购物车中,此商品的总价
        productOrderItem.setProductOrderItemPrice(price);
        productOrderItem.setProductOrderItemProductId(productId);
        productOrderItem.setProductOrderItemUserId(userId);

        int result = productOrderItemService.save(productOrderItem);
        //    insert into productorderitem(productorderitemId,productorderitemNumber,
        //      productorderitemPrice,productorderitemProductId,productorderitemOrderId,
        //      productorderitemUserId,productorderitemUserMessage)
        //       values(#{productOrderItemId},#{productOrderItemNumber},#{productOrderItemPrice},
        //       #{productOrderItemProductId},#{productOrderItemOrderId},
        //       #{productOrderItemUserId},#{productOrderItemUserMessage})
        if (result > 0) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }
}


////////////////////////////////
//转到前台Mall-购物车订单建立页
/*@RequestMapping(value = "order/create/byCart", method = RequestMethod.GET)
public String goToOrderConfirmPageByCart(Map<String, Object> map,
                                         HttpSession session, HttpServletRequest request,
                                         @RequestParam(required = false) Integer[] order_item_list) throws UnsupportedEncodingException {

    //获得登录的用户信息
    User user=getLoginUser(session);
    if (user != null) {
        map.put("user", user);
    } else {
        return "redirect:/toLogin";
    }
    if (order_item_list == null || order_item_list.length == 0) {
        //用户订单项数组不存在，回到购物车页;
        return "redirect:/cart";
    }
    //"通过订单项ID数组获取订单信息";
    List<ProductOrderItem> orderItemList = new ArrayList<>(order_item_list.length);
    for (Integer orderItem_id : order_item_list) {
        orderItemList.add(productOrderItemService.get(orderItem_id));
    }
   //"------检查订单项合法性------");
    if (orderItemList.size() == 0) {
        //"用户订单项获取失败，回到购物车页");
        return "redirect:/cart";
    }
    for (ProductOrderItem orderItem : orderItemList) {
        if (orderItem.getProductOrderItemUser().getUserId() != userId) {
           //"用户订单项与用户不匹配，回到购物车页");
            return "redirect:/cart";
        }
        if (orderItem.getProductOrderItemOrder() != null) {
            //"用户订单项不属于购物车，回到购物车页");
            return "redirect:/cart";
        }
    }
    //验证通过，获取订单项的产品信息;
    double orderTotalPrice = 0.0;
    for (ProductOrderItem orderItem : orderItemList) {
        Product product = productService.get(orderItem.getProductOrderItemProduct().getProductId());
        product.setProductCategory(categoryService.get(product.getProductCategory().getCategoryId()));
        product.setSingleProductImageList(productImageService.getList(product.getProductId(), (byte) 0, new PageUtil(0, 1)));
        orderItem.setProductOrderItemProduct(product);
        orderTotalPrice += orderItem.getProductOrderItemPrice();
    }
    String addressId = "110000";
    String cityAddressId = "110100";
    String districtAddressId = "110101";
    String detailsAddress = null;
    String order_post = null;
    String order_receiver = null;
    String order_phone = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();
            switch (cookieName) {
                case "addressId":
                    addressId = cookieValue;
                    break;
                case "cityAddressId":
                    cityAddressId = cookieValue;
                    break;
                case "districtAddressId":
                    districtAddressId = cookieValue;
                    break;
                case "order_post":
                    order_post = URLDecoder.decode(cookieValue, "UTF-8");
                    break;
                case "order_receiver":
                    order_receiver = URLDecoder.decode(cookieValue, "UTF-8");
                    break;
                case "order_phone":
                    order_phone = URLDecoder.decode(cookieValue, "UTF-8");
                    break;
                case "detailsAddress":
                    detailsAddress = URLDecoder.decode(cookieValue, "UTF-8");
                    break;
            }
        }
    }
   // logger.info("获取省份信息");
    List<Address> addressList = AddressService.getRoot();
    //logger.info("获取addressId为{}的市级地址信息", addressId);
    List<Address> cityAddress = AddressService.getList(null, addressId);
   // logger.info("获取cityAddressId为{}的区级地址信息", cityAddressId);
    List<Address> districtAddress = addressService.getList(null, cityAddressId);

    map.put("orderItemList", orderItemList);
    map.put("addressList", addressList);
    map.put("cityList", cityAddress);
    map.put("districtList", districtAddress);
    map.put("orderTotalPrice", orderTotalPrice);

    map.put("addressId", addressId);
    map.put("cityAddressId", cityAddressId);
    map.put("districtAddressId", districtAddressId);
    map.put("order_post", order_post);
    map.put("order_receiver", order_receiver);
    map.put("order_phone", order_phone);
    map.put("detailsAddress", detailsAddress);

   // logger.info("转到前台Mall-订单建立页");
    return "fore/productBuyPage";
}

}*/
