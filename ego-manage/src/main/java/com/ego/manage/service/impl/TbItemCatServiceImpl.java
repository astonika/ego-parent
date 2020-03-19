package com.ego.manage.service.impl;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.pojo.TbItemCat;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbItemCatServiceImpl implements com.ego.manage.service.TbItemCatService {

    @Reference
    private TbItemCatDubboService tbItemCatDubboService;

    @Override
    public List<EasyUiTree> show(long pid) {
        List<TbItemCat> list = tbItemCatDubboService.show(pid);
        List<EasyUiTree> listTree = new ArrayList<>();
        for (TbItemCat tbItemCat : list) {
            EasyUiTree tree = new EasyUiTree();
            tree.setId(tbItemCat.getId());
            tree.setText(tbItemCat.getName());
            tree.setState(tbItemCat.getIsParent() ? "closed" : "open");
            listTree.add(tree);
        }
        return listTree;
    }

}
