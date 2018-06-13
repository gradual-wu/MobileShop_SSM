package cn.wycclub.dao.mapper;

import cn.wycclub.dao.po.PageSelect;
import cn.wycclub.dao.po.ProductInfo;
import cn.wycclub.dao.po.ProductInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productInfoMapper")
public interface ProductInfoMapper {
    int countByExample(ProductInfoExample example);

    int deleteByExample(ProductInfoExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    List<ProductInfo> selectByExample(ProductInfoExample example);

    List<ProductInfo> selectProductByRadom(Integer count);

    ProductInfo selectByPrimaryKey(Integer pid);

    List<String> selectBrund();

    int updateByExampleSelective(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    int updateByExample(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);
}