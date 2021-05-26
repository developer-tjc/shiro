package com.tang.service.Imp;

import com.tang.mapper.UserMapper;
import com.tang.pojo.User;
import com.tang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User selAll(String uname) {

            return userMapper.selAllUser(uname);
    }
}
