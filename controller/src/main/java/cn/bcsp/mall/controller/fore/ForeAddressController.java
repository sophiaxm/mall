package cn.bcsp.mall.controller.fore;

import cn.bcsp.mall.controller.BaseController;
import cn.bcsp.mall.pojo.Address;
import cn.bcsp.mall.service.AddressService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 地址的控制器
 * @author:
 * @date: 2021年08月30日 16:22
 */
@Controller
public class ForeAddressController extends BaseController {
    @Resource
    private AddressService addressService;
    @GetMapping(value = "/address/{areaId}",
            produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Map<String,Object> getAddressByAreaId(@PathVariable String areaId){

        Map<String,Object> map=new HashMap<>();

        //根据省份的ID或者是直辖市的ID 查询地级市的集合
        List<Address> addressList= addressService.getList(Integer.parseInt(areaId));
        if(addressList==null||addressList.size()==0){
            map.put("success",false);
            return map;
        }

        //查询区级的信息 根据地级市集合中的第一个地级市的ID 查询区级信息
        int addressAreaId=Integer.parseInt(addressList.get(0).getAddressAreaId());
        List<Address> childAddressList=  addressService.getList(addressAreaId);

        //返回的是地级市的信息
        map.put("addressList",addressList);
        map.put("success",true);
        //返回的是区级的信息
        map.put("childAddressList",childAddressList);

        return map;
    }
}
