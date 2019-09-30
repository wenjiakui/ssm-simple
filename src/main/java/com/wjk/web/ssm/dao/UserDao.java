package com.wjk.web.ssm.dao;

import com.wjk.web.ssm.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    User getUserByName(@Param(value = "userName") String userName);

    List<User> getUserList(@Param(value = "status") String status);

    int addUser(@Param(value = "paramUser") User paramUser);
}
