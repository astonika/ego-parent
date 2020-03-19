package com.ego.passport.service.impl;


import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbUserDubboService;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;
import com.ego.redis.dao.JedisDao;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Reference
    private TbUserDubboService tbUserDubboServiceImpl;
    @Resource
    private JedisDao jedisDaoImpl;

    @Override
    public EgoResult login(TbUser tbUser, HttpServletRequest request, HttpServletResponse response) {
        EgoResult egoResult = new EgoResult();
        TbUser userSelect = tbUserDubboServiceImpl.selByUser(tbUser);
        if (userSelect != null) {
            egoResult.setStatus(200);
            // 当用户登录成功后把用户信息放入到redis中
            String key = UUID.randomUUID().toString();
            jedisDaoImpl.set(key, JsonUtils.objectToJson(userSelect));
            jedisDaoImpl.expire(key, 60 * 60 * 24 * 7);
            //产生Cookie
            CookieUtils.setCookie(request, response, "TT_TOKEN", key, 60 * 60 * 24 * 7);
        } else {
            egoResult.setData("用户名和密码错误");
        }
        return egoResult;
    }

    @Override
    public EgoResult getUserInfoByToken(String token) {
        EgoResult egoResult = new EgoResult();
        String json = jedisDaoImpl.get(token);
        if (json != null && !json.equals("")) {
            TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
            tbUser.setPassword(null);
            egoResult.setStatus(200);
            egoResult.setMsg("OK");
            egoResult.setData(tbUser);
        } else {
            egoResult.setMsg("获取失败");
        }
        return egoResult;
    }

    @Override
    public EgoResult logout(String token, HttpServletRequest request, HttpServletResponse response) {
        Long index = jedisDaoImpl.del(token);
        CookieUtils.deleteCookie(request, response, "TT_TOKEN");
        EgoResult er = new EgoResult();
        er.setStatus(200);
        er.setMsg("OK");
        return er;
    }


}
