package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;

import java.util.List;

public interface TbItemDubboService {

    /**
     * 商品分页查询
     *
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGrid show(int page, int rows);

    /**
     * 根据id修改状态
     *
     * @param tbItem
     * @return
     */
    int updItemStatus(TbItem tbItem);

    /**
     * 商品新增
     *
     * @param tbItem
     * @return
     */
    int insTbItem(TbItem tbItem);

    /**
     * 新增包含商品表和商品描述表
     *
     * @param tbItem
     * @param tbItemDesc
     * @param paramItem
     * @return
     * @throws Exception
     */
    int insTbItemDesc(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem paramItem) throws Exception;

    TbItem selById(long id);

    /**
     * 通过状态查询全部可用数据
     *
     * @param status
     * @return
     */
    List<TbItem> selAllByStatus(byte status);
}
