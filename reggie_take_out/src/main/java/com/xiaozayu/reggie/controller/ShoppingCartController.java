package com.xiaozayu.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaozayu.reggie.common.BaseContext;
import com.xiaozayu.reggie.common.R;
import com.xiaozayu.reggie.entity.ShoppingCart;
import com.xiaozayu.reggie.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/04/27/14:35
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加购物车
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        log.info("购物车数据:{}",shoppingCart);
        //设置用户id，指定当前是哪个用户的购物车数据
        Long currentId= BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);
        //查询当前菜品或者套餐是否在购物车里面
        Long dishId=shoppingCart.getDishId();

        LambdaQueryWrapper<ShoppingCart> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,currentId);
        if (dishId!=null){
            //添加的是彩品
            queryWrapper.  eq(ShoppingCart::getDishId,dishId);
        }else {
            //添加到购物车的是套餐
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }
        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);
        if (cartServiceOne!=null){
            //如果存在，加1
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number+1);
            shoppingCartService.updateById(cartServiceOne);

        }else {
            //如果不存在，加入购物车，默认为一
            shoppingCart.setNumber(1);
            shoppingCartService.save(shoppingCart);
            cartServiceOne=shoppingCart;
        }

        return  R.success(cartServiceOne);
    }

    /**
     * 查看购物车
     * @return
     */
    public  R<List<ShoppingCart>> list(){
        log.info("查看购物车...");
        LambdaQueryWrapper<ShoppingCart> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());
        queryWrapper.orderByAsc(ShoppingCart::getCreateTime);
        List<ShoppingCart> list=shoppingCartService.list(queryWrapper);

        return  R.success(list);
    }


}
