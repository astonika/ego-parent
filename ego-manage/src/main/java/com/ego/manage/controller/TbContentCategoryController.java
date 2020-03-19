package com.ego.manage.controller;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.manage.service.TbContentCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TbContentCategoryController {

    @Resource
    private TbContentCategoryService tbContentCategoryServiceImpl;

    @RequestMapping("content/category/list")
    @ResponseBody
    public List<EasyUiTree> showCategory(@RequestParam(defaultValue = "0") long id) {
        return tbContentCategoryServiceImpl.showCategory(id);
    }


}
