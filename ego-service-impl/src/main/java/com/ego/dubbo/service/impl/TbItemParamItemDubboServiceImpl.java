package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbItemParamItemDubboSerice;
import com.ego.mapper.TbItemParamItemMapper;
import com.ego.pojo.TbItemParamItem;

import javax.annotation.Resource;
import java.util.Date;

public class TbItemParamItemDubboServiceImpl implements TbItemParamItemDubboSerice {
    @Resource
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public int insParamItem(TbItemParamItem paramItem) {
        Date date = new Date();
        paramItem.setCreated(date);
        paramItem.setUpdated(date);
        tbItemParamItemMapper.insertSelective(paramItem);
        return 0;
    }


}
