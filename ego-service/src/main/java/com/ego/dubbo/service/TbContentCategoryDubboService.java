package com.ego.dubbo.service;

import com.ego.pojo.TbContentCategory;

import java.util.List;

public interface TbContentCategoryDubboService {
    /**
     * 根据父id查询所有子类目
     *
     * @param pid
     * @return
     */
    List<TbContentCategory> selByPid(long pid);

    /**
     * 新增
     *
     * @param cate
     * @return
     */
    int insTbContentCategory(TbContentCategory cate);

    /**
     * 修改isParent通过id
     *
     * @param cate
     * @return
     */
    int updIsParentById(TbContentCategory cate);
}
