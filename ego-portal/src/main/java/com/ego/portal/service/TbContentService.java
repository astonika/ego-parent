package com.ego.portal.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbContent;

public interface TbContentService {
    /**
     * 分页显示内容信息
     *
     * @param categoryId
     * @param page
     * @param row
     * @return
     */
    EasyUIDataGrid showContent(long categoryId, int page, int row);

    /**
     * 新增
     *
     * @param content
     * @return
     */
    int save(TbContent content);

    /**
     * 显示大广告
     *
     * @return
     */
    String showBigPic();
}
