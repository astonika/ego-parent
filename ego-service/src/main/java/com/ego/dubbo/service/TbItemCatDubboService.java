package com.ego.dubbo.service;

import com.ego.pojo.TbItemCat;

import java.util.List;

public interface TbItemCatDubboService {

    List<TbItemCat> show(long pid);

    /**
     * 根据类目id查询
     *
     * @param id
     * @return
     */
    TbItemCat selById(long id);
}
