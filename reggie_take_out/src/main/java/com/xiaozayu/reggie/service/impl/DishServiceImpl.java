package com.xiaozayu.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaozayu.reggie.dto.DishDto;
import com.xiaozayu.reggie.entity.Dish;
import com.xiaozayu.reggie.entity.DishFlavor;
import com.xiaozayu.reggie.mapper.DishMapper;
import com.xiaozayu.reggie.service.DishFlavorService;
import com.xiaozayu.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Provider;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private DishFlavorService dishFlavorService;
    /**
     * 新增菜名，同时保存对应菜品数据
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        //保存基本信息到dish表
        this.save(dishDto);

        Long dishId = dishDto.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors=flavors.stream().map((item)->{
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        //保存基本信息到dish_flavor表
        dishFlavorService.saveBatch(flavors);
    }

    @Override
    //根据id查询菜品信息和对应口味信息
    public DishDto getByIdWithFlavor(Long id) {
        //查询菜品信息
        Dish dish = this.getById(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);


        //查询菜品对应口味信息
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);

        return dishDto;
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //更新dish表的菜品信息
        this.updateById(dishDto);
        //清理当前菜品对应的口味数据 dish_flavor的delete操作
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(queryWrapper);

        //添加当前提交过来的口味数据 dish_flavor的insert操作
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors=flavors.stream().map((item)->{
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }
}
