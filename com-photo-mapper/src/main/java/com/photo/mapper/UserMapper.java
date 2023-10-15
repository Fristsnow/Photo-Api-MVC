package com.photo.mapper;

import com.photo.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> list();

    User login(String email);

    void createAdmin(User user);

//    User getUser(String email);
}
