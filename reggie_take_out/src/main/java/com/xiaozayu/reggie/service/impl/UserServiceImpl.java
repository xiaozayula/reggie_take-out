package com.xiaozayu.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaozayu.reggie.entity.User;
import com.xiaozayu.reggie.mapper.UserMapper;
import com.xiaozayu.reggie.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/04/25/22:56
 * @Description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>implements UserService {
}
