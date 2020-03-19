package com.ego.manage.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbItemParam;

public interface TbItemParamService {
    EasyUIDataGrid showPage(int page, int rows);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int delete(String ids) throws Exception;

    /**
     * 根据类目id查询模板信息
     *
     * @param catId
     * @return
     */
    EgoResult showParam(long catId);

    /**
     * 新增模板信息
     *
     * @param param
     * @return
     */
    EgoResult save(TbItemParam param);
}
