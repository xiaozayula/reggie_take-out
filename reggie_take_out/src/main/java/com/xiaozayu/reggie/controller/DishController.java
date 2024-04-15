package com.xiaozayu.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaozayu.reggie.common.R;
import com.xiaozayu.reggie.dto.DishDto;
import com.xiaozayu.reggie.entity.Category;
import com.xiaozayu.reggie.entity.Dish;
import com.xiaozayu.reggie.service.CategoryService;
import com.xiaozayu.reggie.service.DishFlavorService;
import com.xiaozayu.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/04/07/15:05
 * @Description:
 */
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增菜品
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){//提交json数据，所以要加@RequestBody
        log.info(dishDto.toString());
        dishService.saveWithFlavor(dishDto);
        return  R.success("新增菜品成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        //构造分页构造器对象
        Page<Dish> pageInfo = new Page<>(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>();


        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();

        //添加过滤条件
        queryWrapper.like(name!=null,Dish::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Dish::getUpdateTime);

        //执行分页查询
        dishService.page(pageInfo,queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo,dishDtoPage,"records");
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list =records.stream().map((item)->
                {
                    DishDto dishDto=new DishDto();

                    BeanUtils.copyProperties(item,dishDto);

                    Long categoryId = item.getCategoryId();//分类id
                    //根据id查询对象
                    Category category = categoryService.getById(categoryId);
                    String categoryName = category.getName();
                    dishDto.setCategoryName(categoryName);
                    return dishDto;
                }
        ).collect(Collectors.toList());

        dishDtoPage.setRecords(list);

        return  R.success(dishDtoPage);
    }

    /**
     * 根据id查询菜品信息和对应口味信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public  R<DishDto> get(@PathVariable Long id){
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return  R.success(dishDto);
    }

    /**
     * 更新菜品
     * @param dishDto
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){//提交json数据，所以要加@RequestBody
        log.info(dishDto.toString());
        dishService.updateWithFlavor(dishDto);
        return  R.success("更新菜品成功");
    }

    /**
     * 根据条件查询对应数据
     * @param dish
     * @return
     */
    @GetMapping("/list")
    public  R<List<Dish>> list(Dish dish){
        //构建查询条件
        LambdaQueryWrapper<Dish> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId()!=null,Dish::getCategoryId,dish.getCategoryId());
        //添加排序条件
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        List<Dish> list = dishService.list(queryWrapper);
        return  R.success(list);
    }
}
