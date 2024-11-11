package com.sjh.springboot_sjh.repository;


import com.sjh.springboot_sjh.pojo.User;
import com.sjh.springboot_sjh.pojo.dto.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}
