package com.sjh.springboot_sjh.service;


import com.sjh.springboot_sjh.pojo.User;
import com.sjh.springboot_sjh.pojo.dto.UserDto;
import com.sjh.springboot_sjh.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User add(UserDto user)
    {
        User userPojo = new User();
        BeanUtils.copyProperties(user, userPojo);
        userPojo.setEmail(user.getEmail());
        userPojo.setPassword(user.getPassword());
        userPojo.setUserName(user.getUserName());


        return userRepository.save(userPojo);
    }

    @Override
    public User get(Integer userId)
    {
        return userRepository.findById(userId).orElseThrow(()-> {
            throw new RuntimeException("User not found");
        });
    }

    @Override
    public User update(UserDto user) {

        User userPojo = new User();
        BeanUtils.copyProperties(user, userPojo);
        userPojo.setEmail(user.getEmail());
        userPojo.setPassword(user.getPassword());
        userPojo.setUserName(user.getUserName());


        return userRepository.save(userPojo);
    }


}


