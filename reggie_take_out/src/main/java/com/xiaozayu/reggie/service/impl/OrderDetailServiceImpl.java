package com.xiaozayu.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaozayu.reggie.entity.OrderDetail;
import com.xiaozayu.reggie.mapper.OrderDetailMapper;
import com.xiaozayu.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/04/27/16:10
 * @Description:
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>implements OrderDetailService{
}
