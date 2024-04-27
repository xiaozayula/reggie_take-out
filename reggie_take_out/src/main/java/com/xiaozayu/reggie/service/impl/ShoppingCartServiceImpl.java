package com.xiaozayu.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaozayu.reggie.entity.ShoppingCart;
import com.xiaozayu.reggie.mapper.ShoppingCartMapper;
import com.xiaozayu.reggie.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/04/27/14:31
 * @Description:
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>implements ShoppingCartService {
}
