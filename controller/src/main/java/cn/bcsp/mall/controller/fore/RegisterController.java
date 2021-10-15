package cn.bcsp.mall.controller.fore;

import cn.bcsp.mall.pojo.Address;
import cn.bcsp.mall.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 注册控制器
 * @author:
 * @date: 2021年08月27日 15:37
 */
@Controller
public class RegisterController {
    @Resource
    private AddressService addressService;

    @RequestMapping("/register")
    public String toRegister(HttpServletRequest request){
        String addressId="110000";  //北京的addressAreaId
        String cityAddressId="110100";  //北京市的addressAreaId
        List<Address> addressList=addressService.selectProvince();   //获取省份或者是直辖市信息

        //根据省份或者是直辖市的addressAreaId 查询下面的地级市的信息
        List<Address> cityAddress=addressService.getList(Integer.parseInt(addressId));

        //根据地级市的addressAreaId查询下面区级的信息
        List<Address> districtAddress=addressService.getList(Integer.parseInt(cityAddressId));

        request.setAttribute("addressList",addressList); //省份的信息
        request.setAttribute("cityList",cityAddress); //城市的信息
        request.setAttribute("districtList",districtAddress); //区级的信息
        request.setAttribute("addressId",addressId);
        request.setAttribute("cityAddressId",cityAddressId);
        return "fore/register";
    }

    @RequestMapping(value = "/register/doRegister",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> doRegister(String userName,String userPassword,
                                         String userNickName,String userBirthday,
                                         int userGender,String userAddress){

        Map<String,Object>  map=new HashMap<>();
        map.put("success",true);

        return map;
    }

}
