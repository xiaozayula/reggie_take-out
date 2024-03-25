package com.xiaozayu.reggie.common;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/03/25/15:56
 * @Description: 基于TreadLocal封装工具类，用于保存和获取当前用户登录用户id
 */
public class BaseContext {
    private static  ThreadLocal<Long> threadLocal=new ThreadLocal<>();
    public  static void setCurrentId(Long id){
        threadLocal.set(id);
    }
    public  static  Long getCurrentId(){
        return threadLocal.get();
    }
}
