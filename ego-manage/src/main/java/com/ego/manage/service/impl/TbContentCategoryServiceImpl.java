package com.ego.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUiTree;
import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.manage.service.TbContentCategoryService;
import com.ego.pojo.TbContentCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

    @Reference
    private TbContentCategoryDubboService tbContentCategoryDubboServiceImpl;

    @Override
    public List<EasyUiTree> showCategory(long id) {
        List<EasyUiTree> listTree = new ArrayList<>();
        List<TbContentCategory> list = tbContentCategoryDubboServiceImpl.selByPid(id);
        for (TbContentCategory cate : list) {
            EasyUiTree tree = new EasyUiTree();
            tree.setId(cate.getId());
            tree.setText(cate.getName());
            tree.setState(cate.getIsParent() ? "closed" : "open");
            listTree.add(tree);
        }
        return listTree;
    }

}
