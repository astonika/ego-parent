package com.ego.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.utils.IDUtils;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.manage.service.TbItemService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TbItemServiceImpl implements TbItemService {

    @Reference
    private TbItemDubboService tbItemDubboService;
    @Reference
    private TbItemDescDubboService tbItemDescDubboService;

    @Override
    public EasyUIDataGrid show(int page, int rows) {
        return tbItemDubboService.show(page, rows);
    }


    @Override
    public int update(String ids, byte status) {
        int index = 0;
        TbItem item = new TbItem();
        String[] idsStr = ids.split(",");
        for (String id : idsStr) {
            item.setId(Long.parseLong(id));
            item.setStatus(status);
            index += tbItemDubboService.updItemStatus(item);
        }
        if (index == idsStr.length) {
            return 1;
        }
        return 0;
    }


    /**
     * 商品新增
     *
     * @param item
     * @param desc
     * @return
     */
    @Override
    public int save(TbItem item, String desc, String itemParams) throws Exception {
        // 不考虑事务回滚
//        long id = IDUtils.genItemId();
//        item.setId(id);
//        Date date = new Date();
//        item.setCreated(date);
//        item.setUpdated(date);
//        item.setStatus((byte) 1);
//        int index = tbItemDubboService.insTbItem(item);
//        if(index>0){
//            TbItemDesc tbItemDesc = new TbItemDesc();
//            tbItemDesc.setItemDesc(desc);
//            tbItemDesc.setItemId(id);
//            tbItemDesc.setCreated(date);
//            tbItemDesc.setUpdated(date);
//            index+=tbItemDescDubboService.insDesc(tbItemDesc);
//        }
//        if (index == 2){
//            return 1;
//        }
        // 调用dubbo中考虑事务回滚功能方法
        long id = IDUtils.genItemId();
        item.setId(id);
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        item.setStatus((byte) 1);
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setItemId(id);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);

        TbItemParamItem paramItem = new TbItemParamItem();
        paramItem.setCreated(date);
        paramItem.setUpdated(date);
        paramItem.setItemId(id);
        paramItem.setParamData(itemParams);


        int index = 0;
        index = tbItemDubboService.insTbItemDesc(item, tbItemDesc, paramItem);
        return index;
    }

}
