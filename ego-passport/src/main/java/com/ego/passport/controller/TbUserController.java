package com.ego.passport.controller;

import com.ego.commons.pojo.EgoResult;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TbUserController {

    @Resource
    private TbUserService tbUserService;

    /**
     * 显示登录页面
     *
     * @return
     */
    @RequestMapping("user/showLogin")
    public String showLogin(@RequestHeader(value = "Referer", defaultValue = "") String url, Model model, String interurl) {
        if (interurl != null && !interurl.equals("")) {
            model.addAttribute("redirect", interurl);
        } else if (!url.equals("")) {
            model.addAttribute("redirect", url);
        }
        return "login";
    }

    /**
     * 登录
     *
     * @param tbUser
     * @return
     */
    @RequestMapping("user/login")
    @ResponseBody
    public EgoResult login(TbUser tbUser, HttpServletRequest request, HttpServletResponse response) {
        return tbUserService.login(tbUser, request, response);
    }

    /**
     * 通过token获取用户信息
     *
     * @param token
     * @param callback
     * @return
     */
    @RequestMapping("user/token/{token}")
    @ResponseBody
    public Object getUserInfoByToken(@PathVariable String token, String callback) {
        EgoResult egoResult = tbUserService.getUserInfoByToken(token);
        if (callback != null && !callback.equals("")) {
            MappingJacksonValue mjv = new MappingJacksonValue(egoResult);
            mjv.setJsonpFunction(callback);
            return mjv;
        }
        return egoResult;
    }

    /**
     * 退出
     *
     * @param token
     * @param callback
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("user/logout/{token}")
    @ResponseBody
    public Object logout(@PathVariable String token, String callback, HttpServletRequest request, HttpServletResponse response) {
        EgoResult egoResult = tbUserService.logout(token, request, response);
        if (callback != null && !callback.equals("")) {
            MappingJacksonValue mjv = new MappingJacksonValue(egoResult);
            mjv.setJsonpFunction(callback);
            return mjv;
        }
        return egoResult;
    }

}
