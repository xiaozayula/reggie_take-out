package com.xiaozayu.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaozayu.reggie.entity.Orders;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/04/27/16:07
 * @Description:
 */
public interface OrderService extends IService<Orders> {
    //用户下单
    public void submit(Orders orders);
}
