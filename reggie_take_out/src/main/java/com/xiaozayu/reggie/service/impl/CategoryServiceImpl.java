package com.xiaozayu.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaozayu.reggie.common.CustomException;
import com.xiaozayu.reggie.entity.Category;
import com.xiaozayu.reggie.entity.Dish;
import com.xiaozayu.reggie.entity.Setmeal;
import com.xiaozayu.reggie.mapper.CategoryMapper;
import com.xiaozayu.reggie.service.CategoryService;
import com.xiaozayu.reggie.service.DishService;
import com.xiaozayu.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/03/26/15:29
 * @Description:
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>implements CategoryService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    //根据id删除分类，删除之前需要判断
    @Override
    public void remove(Long id) {
        //查询当前分类是否关联了分类，如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper=new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行分类
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1=dishService.count(dishLambdaQueryWrapper);
        if(count1>0){
            //已经关联，抛出一个业务异常
            throw  new CustomException("当前分类下关联了菜品,不能删除");
        }

        //查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper=new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行查询
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count();
        if(count2>0) {
            //已经关联，抛出一个业务异常
            throw  new CustomException("当前分类下关联了套餐，不能删除");
        }

        //正常删除分类
        super.removeById(id);
    }
}
