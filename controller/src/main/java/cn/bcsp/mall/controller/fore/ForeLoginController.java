package cn.bcsp.mall.controller.fore;

import cn.bcsp.mall.controller.BaseController;
import cn.bcsp.mall.pojo.User;
import cn.bcsp.mall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 前端登录控制器
 * @author: 王跃
 * @date: 2021年08月26日 20:47
 */
@Controller
public class ForeLoginController  extends BaseController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login/doLogin",produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Map<String,Object> doLogin(@RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                      HttpSession session){
        Map<String,Object> map=new HashMap<>();
        User user= userService.login(username,password);
        if(user==null){
            map.put("success",false);
        }else {
            map.put("success",true);
            session.setAttribute("user",user);
        }
        return map;
    }

    //去登录页面
    @RequestMapping("/login")
    public String login(){
        return "fore/loginPage";
    }

    //退出
    @RequestMapping("/login/logout")
    public String logout(HttpSession session){
        User user= getLoginUser(session);
        if(user!=null){  //如果session中有用户信息(说明用户已经登录),销毁session中的所有数据
            session.invalidate();
        }
        return "redirect:/login";
    }


}
