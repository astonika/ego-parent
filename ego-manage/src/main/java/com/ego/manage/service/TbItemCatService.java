package com.ego.manage.service;

import com.ego.commons.pojo.EasyUiTree;

import java.util.List;

public interface TbItemCatService {
    List<EasyUiTree> show(long pid);
}
