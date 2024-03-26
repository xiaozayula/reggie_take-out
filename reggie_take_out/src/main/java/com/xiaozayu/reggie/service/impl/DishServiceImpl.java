package com.xiaozayu.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaozayu.reggie.entity.Dish;
import com.xiaozayu.reggie.mapper.DishMapper;
import com.xiaozayu.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Provider;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/03/26/20:19
 * @Description:
 */
@Service
@Slf4j
public class DishServiceImpl  extends ServiceImpl<DishMapper, Dish>implements DishService {
}
