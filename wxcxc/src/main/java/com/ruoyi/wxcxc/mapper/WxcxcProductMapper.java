package com.ruoyi.wxcxc.mapper;

import java.util.List;
import com.ruoyi.wxcxc.domain.WxcxcProduct;
import com.ruoyi.wxcxc.domain.WxcxcProductSensor;

/**
 * 产品管理Mapper接口
 * 
 * @author l62202
 * @date 2021-10-30
 */
public interface WxcxcProductMapper 
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
     * 删除产品管理
     * 
     * @param id 产品管理主键
     * @return 结果
     */
    public int deleteWxcxcProductById(Long id);

    /**
     * 批量删除产品管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxcProductByIds(Long[] ids);

    /**
     * 批量删除产品传感器管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxcxcProductSensorByProductIds(Long[] ids);
    
    /**
     * 批量新增产品传感器管理
     * 
     * @param wxcxcProductSensorList 产品传感器管理列表
     * @return 结果
     */
    public int batchWxcxcProductSensor(List<WxcxcProductSensor> wxcxcProductSensorList);
    

    /**
     * 通过产品管理主键删除产品传感器管理信息
     * 
     * @param id 产品管理ID
     * @return 结果
     */
    public int deleteWxcxcProductSensorByProductId(Long id);
}
