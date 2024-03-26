package com.xiaozayu.reggie.common;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/03/26/21:05
 * @Description:
 */
//自定义业务异常
public class CustomException extends  RuntimeException {
    public  CustomException(String message){
        super(message);
    }
}
