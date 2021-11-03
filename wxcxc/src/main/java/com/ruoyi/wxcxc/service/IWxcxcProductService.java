package com.ruoyi.wxcxc.service;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcProduct;

/**
 * 产品管理Service接口
 * 
 * @author l62202
 * @date 2021-10-30
 */
public interface IWxcxcProductService 
{
    /**
     * 查询产品管理
     * 
     * @param id 产品管理主键
     * @return 产品管理
     */
    public WxcxcProduct selectWxcxcProductById(Long id);

    /**
     * 查询产品管理列表
     * 
     * @param wxcxcProduct 产品管理
     * @return 产品管理集合
     */
    public List<WxcxcProduct> selectWxcxcProductList(WxcxcProduct wxcxcProduct);

    /**
     * 新增产品管理
     * 
     * @param wxcxcProduct 产品管理
     * @return 结果
     */
    public int insertWxcxcProduct(WxcxcProduct wxcxcProduct);

    /**
     * 修改产品管理
     * 
     * @param wxcxcProduct 产品管理
     * @return 结果
     */
    public int updateWxcxcProduct(WxcxcProduct wxcxcProduct);

    /**
     * 批量删除产品管理
     * 
     * @param ids 需要删除的产品管理主键集合
     * @return 结果
     */
    public int deleteWxcxcProductByIds(Long[] ids);

    /**
     * 删除产品管理信息
     * 
     * @param id 产品管理主键
     * @return 结果
     */
    public int deleteWxcxcProductById(Long id);
}
