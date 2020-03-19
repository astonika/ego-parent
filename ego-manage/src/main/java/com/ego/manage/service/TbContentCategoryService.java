package com.ego.manage.service;

import com.ego.commons.pojo.EasyUiTree;

import java.util.List;

public interface TbContentCategoryService {
    /**
     * 查询所有类目并转换为easyui tree的属性要求
     *
     * @return
     */
    List<EasyUiTree> showCategory(long id);
}
