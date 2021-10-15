package cn.bcsp.mall.service;

import cn.bcsp.mall.pojo.Address;

import java.util.List;

public interface AddressService {
    //查询所有的省份信息
    List<Address> selectProvince();

    //查询区级信息或者是地级市的信息 根据addressRegionId
    List<Address> getList(int addressRegionId);

    /////////////////////////////////////////////

}