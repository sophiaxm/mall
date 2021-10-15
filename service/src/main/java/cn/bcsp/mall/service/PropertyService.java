package cn.bcsp.mall.service;

import cn.bcsp.mall.pojo.Property;

import java.util.List;

public interface PropertyService {
    List<Property> getAll(int categoryId);
}