package com.xiaozayu.test;

import org.junit.jupiter.api.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/03/28/20:24
 * @Description:
 */
public class UploadFileTest {
    @Test
    public  void  test1(){
        String fileName="ererewe.jpg";
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffix);

    }
}
