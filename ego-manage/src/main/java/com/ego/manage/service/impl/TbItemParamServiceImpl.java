package com.ego.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.manage.pojo.TbItemParamChild;
import com.ego.manage.service.TbItemParamService;
import com.ego.pojo.TbItemParam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TbItemParamServiceImpl implements TbItemParamService {

    @Reference
    private TbItemParamDubboService tbItemParamDubboServiceImpl;
    @Reference
    private TbItemCatDubboService tbItemCatDubboServiceImp;


    @Override
    public EasyUIDataGrid showPage(int page, int rows) {
        EasyUIDataGrid dataGrid = tbItemParamDubboServiceImpl.showPage(page, rows);
        List<TbItemParam> list = (List<TbItemParam>) dataGrid.getRows();
        List<TbItemParamChild> listChild = new ArrayList<>();
        for (TbItemParam tbItemParam : list) {
            TbItemParamChild child = new TbItemParamChild();
            child.setCreated(tbItemParam.getCreated());
            child.setId(tbItemParam.getId());
            child.setItemCatId(tbItemParam.getItemCatId());
            child.setParamData(tbItemParam.getParamData());
            child.setUpdated(tbItemParam.getUpdated());
            child.setItemCatName(tbItemCatDubboServiceImp.selById(child.getItemCatId()).getName());
            listChild.add(child);
        }
        dataGrid.setRows(listChild);
        return dataGrid;
    }

    @Override
    public int delete(String ids) throws Exception {
        return tbItemParamDubboServiceImpl.delByIds(ids);
    }

    @Override
    public EgoResult showParam(long catId) {
        EgoResult er = new EgoResult();
        TbItemParam param = tbItemParamDubboServiceImpl.selByCatid(catId);
        if (param != null) {
            er.setStatus(200);
            er.setData(param);
        }
        return er;
    }

    @Override
    public EgoResult save(TbItemParam param) {
        Date date = new Date();
        param.setCreated(date);
        param.setUpdated(date);
        int index = tbItemParamDubboServiceImpl.insParam(param);
        EgoResult er = new EgoResult();
        if (index > 0) {
            er.setStatus(200);
        }
        return er;
    }

}
