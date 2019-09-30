package com.wjk.web.ssm.service;


import com.wjk.web.ssm.model.User;

import java.util.List;

public interface UserService {
    User getUserByName(String userName);

    List<User> getUserList(String status);

    String addUser(User paramUser);
}
