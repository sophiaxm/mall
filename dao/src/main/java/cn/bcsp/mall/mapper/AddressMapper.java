package cn.bcsp.mall.mapper;

import cn.bcsp.mall.pojo.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AddressMapper {
    //查询所有的省份信息
    List<Address> selectProvince();

    // 查询地级市的信息或者是区级的信息
    List<Address> select(@Param("addressRegionId") int addressRegionId);
}