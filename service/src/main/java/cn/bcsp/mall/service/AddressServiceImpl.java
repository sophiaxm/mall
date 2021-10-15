package cn.bcsp.mall.service;

import cn.bcsp.mall.mapper.AddressMapper;
import cn.bcsp.mall.pojo.Address;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 地址的业务层
 * @author: 王跃
 * @date: 2021年08月30日 14:39
 */
@Service("addressService")
public class AddressServiceImpl implements  AddressService {

    @Resource
    private AddressMapper addressMapper;

    @Override
    public List<Address> selectProvince() {
        return addressMapper.selectProvince();
    }


    @Override
    public List<Address> getList(int addressRegionId) {
        return addressMapper.select(addressRegionId);
    }

    //////////


}
