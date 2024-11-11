package com.sjh.springboot_sjh.service;


import com.sjh.springboot_sjh.pojo.User;
import com.sjh.springboot_sjh.pojo.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    User add(UserDto user);

    User get(Integer userId);

    User update(UserDto user);

    void login(String userName, String password);
    // 新增方法：获取个人信息
    User getProfile(Integer userId);  // 根据用户 ID 获取用户信息
}
