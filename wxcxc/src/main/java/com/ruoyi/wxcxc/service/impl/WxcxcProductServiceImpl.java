package com.ruoyi.wxcxc.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.wxcxc.domain.WxcxcProductSensor;
import com.ruoyi.wxcxc.mapper.WxcxcProductMapper;
import com.ruoyi.wxcxc.domain.WxcxcProduct;
import com.ruoyi.wxcxc.service.IWxcxcProductService;

/**
 * 产品管理Service业务层处理
 * 
 * @author l62202
 * @date 2021-10-30
 */
@Service
public class WxcxcProductServiceImpl implements IWxcxcProductService 
{
    @Autowired
    private WxcxcProductMapper wxcxcProductMapper;

    /**
     * 查询产品管理
     * 
     * @param id 产品管理主键
     * @return 产品管理
     */
    @Override
    public WxcxcProduct selectWxcxcProductById(Long id)
    {
        return wxcxcProductMapper.selectWxcxcProductById(id);
    }

    /**
     * 查询产品管理列表
     * 
     * @param wxcxcProduct 产品管理
     * @return 产品管理
     */
    @Override
    public List<WxcxcProduct> selectWxcxcProductList(WxcxcProduct wxcxcProduct)
    {
        return wxcxcProductMapper.selectWxcxcProductList(wxcxcProduct);
    }

    /**
     * 新增产品管理
     * 
     * @param wxcxcProduct 产品管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertWxcxcProduct(WxcxcProduct wxcxcProduct)
    {
        wxcxcProduct.setCreateTime(DateUtils.getNowDate());
        int rows = wxcxcProductMapper.insertWxcxcProduct(wxcxcProduct);
        insertWxcxcProductSensor(wxcxcProduct);
        return rows;
    }

    /**
     * 修改产品管理
     * 
     * @param wxcxcProduct 产品管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateWxcxcProduct(WxcxcProduct wxcxcProduct)
    {
        wxcxcProduct.setUpdateTime(DateUtils.getNowDate());
        wxcxcProductMapper.deleteWxcxcProductSensorByProductId(wxcxcProduct.getId());
        insertWxcxcProductSensor(wxcxcProduct);
        return wxcxcProductMapper.updateWxcxcProduct(wxcxcProduct);
    }

    /**
     * 批量删除产品管理
     * 
     * @param ids 需要删除的产品管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteWxcxcProductByIds(Long[] ids)
    {
        wxcxcProductMapper.deleteWxcxcProductSensorByProductIds(ids);
        return wxcxcProductMapper.deleteWxcxcProductByIds(ids);
    }

    /**
     * 删除产品管理信息
     * 
     * @param id 产品管理主键
     * @return 结果
     */
    @Override
    public int deleteWxcxcProductById(Long id)
    {
        wxcxcProductMapper.deleteWxcxcProductSensorByProductId(id);
        return wxcxcProductMapper.deleteWxcxcProductById(id);
    }

    /**
     * 新增产品传感器管理信息
     * 
     * @param wxcxcProduct 产品管理对象
     */
    public void insertWxcxcProductSensor(WxcxcProduct wxcxcProduct)
    {
        List<WxcxcProductSensor> wxcxcProductSensorList = wxcxcProduct.getWxcxcProductSensorList();
        Long id = wxcxcProduct.getId();
        if (StringUtils.isNotNull(wxcxcProductSensorList))
        {
            List<WxcxcProductSensor> list = new ArrayList<WxcxcProductSensor>();
            for (WxcxcProductSensor wxcxcProductSensor : wxcxcProductSensorList)
            {
                wxcxcProductSensor.setProductId(id);
                list.add(wxcxcProductSensor);
            }
            if (list.size() > 0)
            {
                wxcxcProductMapper.batchWxcxcProductSensor(list);
            }
        }
    }
}
