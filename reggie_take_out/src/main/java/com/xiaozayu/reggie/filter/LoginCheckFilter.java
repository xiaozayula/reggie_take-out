package com.xiaozayu.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.xiaozayu.reggie.common.BaseContext;
import com.xiaozayu.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/03/21/19:40
 * @Description:检查用户是否完成登录的过滤器
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static  final AntPathMatcher PATH_MATCHER=new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;//向下转型 请求
        HttpServletResponse response=(HttpServletResponse) servletResponse;//响应
        //1.获取本次请求的URI
        String requestURI=request.getRequestURI();//backend/index.html

        //定义不需要处理的路径
        String[] urls=new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**"
        };
        //2.判断请求是否需要处理
        boolean check = check(urls, requestURI);

        //3.如果不需要处理，则直接放行
        if(check){
            filterChain.doFilter(request,response);
            return;
        }
        //4.判断登录状态，如果已经登录，直接放行
        //Session会话，Attribute属性
        if(request.getSession().getAttribute("employee")!=null){
            Long empId =(Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            //filterChain过滤器链
            filterChain.doFilter(request,response);
            return;
        }
        //5.如果未登录返回为登录，通过输出流方式向客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }
    //路径匹配，检查本次请求是否需要放行
    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return  true;
            }
        }
        return  false;
    }
}
