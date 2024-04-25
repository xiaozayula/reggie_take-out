package com.xiaozayu.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaozayu.reggie.dto.SetmealDto;
import com.xiaozayu.reggie.entity.Setmeal;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/03/26/20:20
 * @Description:
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public  void saveWithDish(SetmealDto setmealDto);

    public  void removeWithDish(List<Long> ids);
}
