package com.wjk.web.ssm.controller;

import com.wjk.web.ssm.model.User;
import com.wjk.web.ssm.service.UserService;
import com.wjk.web.ssm.utils.DateJsonValueProcessor;
import com.wjk.web.ssm.utils.ResultUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Bean
    JsonConfig jsonConfig(){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
        return  jsonConfig;
    }





    @RequestMapping(value = "/getUserByName",method = RequestMethod.GET)
    @ResponseBody
    public ResultUtil getUserByName(@RequestParam(value = "username",required = true)  String username){
        log.info("RequestParam = "+username);
        User user = userService.getUserByName(username);
        if(user!=null){
            return ResultUtil.success(JSONObject.fromObject(user,jsonConfig()));
        }else {
            return ResultUtil.failed(JSONObject.fromObject("{}"));
        }
    }
    @RequestMapping(value = "getUserList",method = RequestMethod.GET)
    @ResponseBody
    public ResultUtil getUserList(){
        log.info("执行 getUserList ！");
        List<User> userList = userService.getUserList("10");
        if(userList.size()>0){
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
            //jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor());
            return ResultUtil.success(JSONArray.fromObject(userList,jsonConfig));
        }else{
            return ResultUtil.failed(JSONObject.fromObject("{}"));
        }
    }

    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    @ResponseBody
    public ResultUtil addUser(User paramUser){
        log.info("add user!");
        String userId = userService.addUser(paramUser);

        JSONObject jsonObject = JSONObject.fromObject("{}");
        Map map = new HashMap();
        map.put("id",userId);
        map.put("time",new Date(System.currentTimeMillis()));
        jsonObject.putAll(map,jsonConfig());
        return ResultUtil.success(jsonObject);
    }

}
