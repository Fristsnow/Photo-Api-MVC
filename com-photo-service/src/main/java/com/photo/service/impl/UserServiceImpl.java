package com.photo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.photo.common.Result;
import com.photo.mapper.UserMapper;
import com.photo.model.User;
import com.photo.model.vo.LoginVo;
import com.photo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public User login(String email) {
        return userMapper.login(email);
    }

    @Override
    public void createAdmin(User user) {
        user.setEmail(user.getEmail());
        user.setFullName(user.getFullName());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        user.setIsAdmin(1);
        user.setCreatedAt(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userMapper.createAdmin(user);
    }

//    @Override
//    public Result<Map<String, Object>> login(LoginVo loginVo) {
//        User modelUser = userMapper.getUser(loginVo.getEmail());
//        log.info("密码：{},{}", modelUser.getPassword(), loginVo.getPassword());
//        if (ObjectUtil.isNotNull(modelUser)) {
////            String password = loginVo.getPassword();
//            String password = DigestUtils.md5DigestAsHex(loginVo.getPassword().getBytes());
//            if (modelUser.getPassword().equals(password)) {
//                String key = UUID.randomUUID().toString();
//                String userCache = JSONUtil.toJsonStr(modelUser);
//                log.info("用户登录=>{}", userCache);
//                Map<String, Object> map = new HashMap<>();
//                map.put("Authorization", key);
//                map.put("userInfo", modelUser);
//                return Result.success(map);
//            }
//            return Result.error(422, "password not");
//        }
//        return Result.error401();
//    }
}
