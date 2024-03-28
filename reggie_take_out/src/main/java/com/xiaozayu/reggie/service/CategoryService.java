package com.xiaozayu.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaozayu.reggie.entity.Category;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/03/26/15:28
 * @Description:
 */
public interface CategoryService extends IService<Category> {

    public  void remove(Long id);
}
