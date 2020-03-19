package com.ego.dubbo.service;

import com.ego.pojo.TbItemParamItem;

public interface TbItemParamItemDubboSerice {
    /**
     * 新增商品到具体规格信息
     *
     * @param paramItem
     * @return
     */
    int insParamItem(TbItemParamItem paramItem);
}
