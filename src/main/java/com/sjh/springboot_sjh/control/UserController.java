package com.sjh.springboot_sjh.control;


import com.sjh.springboot_sjh.pojo.ResponseMessage;
import com.sjh.springboot_sjh.pojo.User;
import com.sjh.springboot_sjh.pojo.dto.UserDto;
import com.sjh.springboot_sjh.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping
    public ResponseMessage<User> add(@Validated  @RequestBody UserDto user)
    {
        User userNew = userService.add(user);
        return ResponseMessage.success(userNew);
    }

    @GetMapping("/{userId}")
    public ResponseMessage<User> get(@PathVariable Integer userId)
    {
        User user = userService.get(userId);
        return ResponseMessage.success(user);
    }

    @PutMapping
    public ResponseMessage<User> update(@RequestBody UserDto user)//这个好像逻辑有问题,直接调用了add了
    {
        User userNew = userService.update(user);
        return ResponseMessage.success(userNew);
    }
}
