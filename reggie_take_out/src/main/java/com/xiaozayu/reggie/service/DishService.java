package com.xiaozayu.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaozayu.reggie.dto.DishDto;
import com.xiaozayu.reggie.entity.Dish;
import com.xiaozayu.reggie.entity.DishFlavor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/03/26/20:18
 * @Description:
 */

public interface DishService extends IService<Dish> {

    //新增菜品，同时新增菜品对应的口味数据，操作dish和dishFlavor两张表

    public void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和对应口味信息
    public DishDto getByIdWithFlavor(Long id);

}
