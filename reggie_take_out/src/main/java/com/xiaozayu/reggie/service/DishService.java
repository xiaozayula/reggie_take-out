package com.xiaozayu.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaozayu.reggie.dto.DishDto;
import com.xiaozayu.reggie.entity.Dish;
import com.xiaozayu.reggie.entity.DishFlavor;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/03/26/20:18
 * @Description:
 */

public interface DishService extends IService<Dish> {

    public void saveWithFlavor(DishDto dishDto);

}
