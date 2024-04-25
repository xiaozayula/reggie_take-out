package com.xiaozayu.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaozayu.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/04/25/22:54
 * @Description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
