package com.wjk.web.ssm.controller;

import com.wjk.web.ssm.utils.ResultUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class LoginController {


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil logout() {
        JSONObject jsonObject = JSONObject.fromObject("{}");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return  ResultUtil.success(jsonObject,"退出成功");
    }

    @RequestMapping(value = "/loginByUserName", method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil loginByUserName(String userName, String password) {
        JSONObject jsonObject = JSONObject.fromObject("{}");
        Map map = new HashMap();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            subject.login(token);
            map.put("token","1234567890fvadfasdfasdfasdf");
            jsonObject.putAll(map);
            return  ResultUtil.success(jsonObject,"登录成功");
        } catch (UnknownAccountException e) {
            return  ResultUtil.failed(jsonObject,"用户不存在");
        } catch (IncorrectCredentialsException e) {
           // e.printStackTrace();
            return  ResultUtil.failed(jsonObject,"密码错误");
        }
    }
}
