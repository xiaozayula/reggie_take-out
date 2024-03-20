package com.xiaozayu.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaozayu.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/03/20/19:57
 * @Description:
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee>{
}
