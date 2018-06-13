package cn.wycclub.dao.mapper;

import cn.wycclub.dao.po.ProductCart;
import cn.wycclub.dao.po.ProductCartExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productCartMapper")
public interface ProductCartMapper {
    int countByExample(ProductCartExample example);

    int deleteByExample(ProductCartExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(ProductCart record);

    int insertSelective(ProductCart record);

    List<ProductCart> selectByExample(ProductCartExample example);

    ProductCart selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") ProductCart record, @Param("example") ProductCartExample example);

    int updateByExample(@Param("record") ProductCart record, @Param("example") ProductCartExample example);

    int updateByPrimaryKeySelective(ProductCart record);

    int updateByPrimaryKey(ProductCart record);
}