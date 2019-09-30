package com.wjk.web.ssm.service.impl;

import com.wjk.web.ssm.dao.UserDao;
import com.wjk.web.ssm.model.User;
import com.wjk.web.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static Logger log = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    @Override
    public List<User> getUserList(String status) {
        return userDao.getUserList(status);
    }

    @Override
    public String addUser(User paramUser) {
        String userId = UUID.randomUUID().toString();
        userId = userId.replace("-","");
        log.info(userId);
        paramUser.setId(userId);
        paramUser.setStatus("10");
        paramUser.setCreateTime(new Date());
        int addResult = userDao.addUser(paramUser);
        if(addResult>0){
            return userId;
        }
        return "";
    }
}
