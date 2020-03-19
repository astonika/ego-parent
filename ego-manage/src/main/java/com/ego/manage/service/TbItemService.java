package com.ego.manage.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;

public interface TbItemService {

    EasyUIDataGrid show(int page, int rows);

    /**
     * 批量修改商品状态
     *
     * @param ids
     * @param status
     * @return
     */
    int update(String ids, byte status);

    int save(TbItem item, String desc, String itemParams) throws Exception;
}
