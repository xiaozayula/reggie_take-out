package com.xiaozayu.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaozayu.reggie.entity.Employee;
import com.xiaozayu.reggie.mapper.EmployeeMapper;
import com.xiaozayu.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/03/20/20:01
 * @Description:
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>implements EmployeeService {
}
