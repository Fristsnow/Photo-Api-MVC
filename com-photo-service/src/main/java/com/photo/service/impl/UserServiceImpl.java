package com.photo.service.impl;

import com.photo.mapper.UserMapper;
import com.photo.model.User;
import com.photo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * 创建Admin
     * @param user
     */
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

    @Override
    public List<User> listClient() {
        return userMapper.listClient();
    }

    /**
     * 通过id查找
     * @param id
     * @return
     */
    @Override
    public User userById(Integer id) {
        return userMapper.userById(id);
    }

    /**
     * 改密码
     * @param id
     * @return
     */
    @Override
    public void resetPassword(Integer id) {
        String password = DigestUtils.md5DigestAsHex("123456".getBytes());
        userMapper.resetPassword(id,password);
    }

    /**
     * 创建Client
     * @param user
     */
    @Override
    public void createClient(User user) {
        user.setEmail(user.getEmail());
        user.setUsername(user.getUsername());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setIsAdmin(0);
        user.setCreatedAt(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userMapper.createAdmin(user);
    }

    /**
     * 通过id删除用户
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
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
