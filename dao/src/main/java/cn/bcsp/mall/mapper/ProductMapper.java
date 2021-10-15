package cn.bcsp.mall.mapper;

import cn.bcsp.mall.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 商品的数据访问层
 * @author: 王跃
 * @date: 2021年08月31日 14:48
 */
@Mapper
public interface ProductMapper {


    //根据商品的ID查询商品的销售数量
    int saleCount(@Param("pid") int pid);

    //根据商品的分类ID 查询该分类下的8条商品信息
    List<Product> selectProductByCategoryId(@Param("categoryId") int categoryId);

    //根据商品的状态查询前size条商品信息
    List<Product> selectProductByProductIsEnabled(
                                @Param("productIsEnabled") int productIsEnabled,
                                @Param("size") int size);

    //根据商品的名字查询商品的信息
    List<Product> selectProductByName(@Param("productName") String productName,
                                      @Param("startRow") int startRow,
                                      @Param("pageSize") int pageSize);
    //根据商品的名字查询商品的总记录数
    int count(@Param("productName") String productName);

    //根据商品的ID查询商品的信息
    Product selectOne(@Param("productId") int productId);

    //根据商品的分类的ID查询商品的总记录数
    int selectCount(@Param("categoryId") int categoryId);

    //根据商品的分类的ID查询商品信息(正常出售 促销的商品)
    List<Product> selectProductByCategoryId2(@Param("categoryId") int categoryId,
                                             @Param("startRow")  int startRow,
                                             @Param("pageSize")  int pageSize);

}
