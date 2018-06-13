package cn.wycclub.service;

import cn.wycclub.dao.mapper.ProductInfoMapper;
import cn.wycclub.dao.po.PageSelect;
import cn.wycclub.dao.po.ProductInfo;
import cn.wycclub.dao.po.ProductInfoExample;
import cn.wycclub.dto.PageInputByProduct;
import cn.wycclub.dto.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品业务逻辑层
 *
 * @author WuYuchen
 * @create 2018-02-14 14:28
 **/

@Service
public class BusinessProductServiceImpl implements BusinessProductService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    /**
     * 获取商品分页
     * */
    @Override
    public PageResult<ProductInfo> getProductPage(PageInputByProduct pageInputByProduct) {
        PageResult<ProductInfo> pageResult = new PageResult<>();
        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();

        //判断分页条件中是否包含品牌信息
        String brand = pageInputByProduct.getBrand();
        if (brand != null && !"".equals(brand)) {
            criteria.andBrandEqualTo(brand);
        }

        //分页查询信息
        PageSelect pageSelect = new PageSelect();
        pageSelect.setOffset(pageInputByProduct.getStartIndex());
        pageSelect.setLimit(pageInputByProduct.getPageSize());
        example.setPageSelect(pageSelect);

        //返回分页信息整理
        pageResult.setList(productInfoMapper.selectByExample(example));
        pageResult.setCurrentPage(pageInputByProduct.getCurrentPage());
        pageResult.setPageSize(pageInputByProduct.getPageSize());
        pageResult.setTotalRecord(productInfoMapper.countByExample(example));

        return pageResult;
    }

    @Override
    public ProductInfo getProductById(Integer id) {
        return productInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductInfo> getProductByIndexPage(Integer count) {
        return productInfoMapper.selectProductByRadom(count);
    }

    @Override
    public List<String> getAllBrand() {
        return productInfoMapper.selectBrund();
    }
}
