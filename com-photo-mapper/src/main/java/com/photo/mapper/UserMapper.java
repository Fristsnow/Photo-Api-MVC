package com.photo.mapper;

import com.photo.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> list();

    User login(String email);

    void createAdmin(User user);

    List<User> listClient();

    /**
     * 通过id查找
     * @param id
     * @return
     */
    User userById(Integer id);

    /**
     * 改密码
     * @param id
     * @param password
     * @return
     */
    void resetPassword(Integer id, String password);

    void deleteById(Integer id);


//    User getUser(String email);
}
