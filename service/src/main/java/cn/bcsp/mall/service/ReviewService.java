package cn.bcsp.mall.service;

import org.apache.ibatis.annotations.Param;

public interface ReviewService {

    int queryCount(int productId);
}