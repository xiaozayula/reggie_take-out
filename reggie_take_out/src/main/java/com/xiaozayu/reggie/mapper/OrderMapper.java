package com.xiaozayu.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaozayu.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/04/27/16:05
 * @Description:
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
