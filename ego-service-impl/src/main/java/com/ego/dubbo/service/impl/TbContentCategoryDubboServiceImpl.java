package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.mapper.TbContentCategoryMapper;
import com.ego.pojo.TbContentCategory;
import com.ego.pojo.TbContentCategoryExample;

import javax.annotation.Resource;
import java.util.List;

public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService {
    @Resource
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<TbContentCategory> selByPid(long pid) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(pid);
        return tbContentCategoryMapper.selectByExample(example);
    }

    @Override
    public int insTbContentCategory(TbContentCategory cate) {
        return tbContentCategoryMapper.insertSelective(cate);
    }

    @Override
    public int updIsParentById(TbContentCategory cate) {
        return tbContentCategoryMapper.updateByPrimaryKeySelective(cate);
    }

}
