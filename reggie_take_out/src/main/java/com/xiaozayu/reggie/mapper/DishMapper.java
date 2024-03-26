package com.xiaozayu.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaozayu.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/03/26/20:16
 * @Description:
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
