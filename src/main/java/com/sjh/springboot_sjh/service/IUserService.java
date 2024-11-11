package com.sjh.springboot_sjh.service;


import com.sjh.springboot_sjh.pojo.User;
import com.sjh.springboot_sjh.pojo.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    User add(UserDto user);

    User get(Integer userId);

    User update(UserDto user);
}
