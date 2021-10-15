package cn.bcsp.mall.service;

import cn.bcsp.mall.mapper.ReviewMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 评论的业务层
 * @author: 王跃
 * @date: 2021年09月01日 16:48
 */
@Service("reviewService")
public class ReviewServiceImpl implements  ReviewService {

    @Resource
    private ReviewMapper reviewMapper;

    @Override
    public int queryCount(int productId) {
        return reviewMapper.selecCount(productId);
    }
}
