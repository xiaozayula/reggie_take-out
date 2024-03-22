package com.xiaozayu.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/03/23/0:10
 * @Description: 全局异常处理
 */
@ControllerAdvice(annotations = {RequestAttribute.class, Controller.class})//annotations注解
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    //进行异常处理
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)//表示处理SQLIntegrityConstraintViolationException这种异常
    public  R<String> excceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());
        if(ex.getMessage().contains("Duplicate entry")){
            String[] split=ex.getMessage().split("");
            String msg=split[2]+"已存在";
            return R.error(msg);
        }
        return R.error("失败");
    }
}
