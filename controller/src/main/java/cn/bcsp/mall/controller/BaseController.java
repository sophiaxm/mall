package cn.bcsp.mall.controller;

import cn.bcsp.mall.pojo.User;

import javax.servlet.http.HttpSession;

/**
 * @Description: 控制器的父类,开发时的一些通用代码
 * @author:
 * @date: 2021年08月27日 15:21
 */
public class BaseController {

    //获得登录用户信息
    protected User getLoginUser(HttpSession session){
         Object object= session.getAttribute("user");
         if(object!=null){
             User user=(User)object;
             return user;
         }
         return null;
    }

}
