package com.photo.service;

import com.photo.common.Result;
import com.photo.model.User;
import com.photo.model.vo.LoginVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

//@Transactional
public interface UserService {
    List<User> list();

    User login(String email);

    void createAdmin(User user);

    List<User> listClient();

    /**
     * 通过id查找用户
     * @param id
     * @return
     */
    User userById(Integer id);

    /**
     * 改密码
     * @param id
     * @return
     */
    void resetPassword(Integer id);

    void createClient(User user);

    void deleteById(Integer id);


//    Result<Map<String, Object>> login(LoginVo loginVo);
}
