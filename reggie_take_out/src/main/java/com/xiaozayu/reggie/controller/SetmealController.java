package com.xiaozayu.reggie.controller;

import com.xiaozayu.reggie.common.R;
import com.xiaozayu.reggie.dto.SetmealDto;
import com.xiaozayu.reggie.service.SetmealDishService;
import com.xiaozayu.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/04/15/11:10
 * @Description:套餐管理
 */
@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private SetmealDishService setmealDishService;
    //新增套餐
    @PostMapping
    public R<String>  save (@RequestBody SetmealDto setmealDto){
        log.info("套餐信息：{}",setmealDto);
        setmealService.saveWithDish(setmealDto);
        return  R.success("新增套餐成功");
    }

}
