package com.ego.manage.controller;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.manage.service.TbItemCatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TbItemCatController {

    @Resource
    private TbItemCatService tbItemCatService;

    @RequestMapping("item/cat/list")
    @ResponseBody
    public List<EasyUiTree> showCat(@RequestParam(defaultValue = "0") long id) {
        return tbItemCatService.show(id);
    }

}
