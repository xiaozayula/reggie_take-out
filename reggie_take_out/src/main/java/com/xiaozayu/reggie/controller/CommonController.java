package com.xiaozayu.reggie.controller;

import com.xiaozayu.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangyu
 * @Date: 2024/03/28/19:46
 * @Description: 文件上传和下载
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${reggie.path}" )
    private String basePath;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        //file是临时文件，需要转存到指定位置，否则此次请求完成后临时文件会被删除
        log.info(file.toString());

        //获得原始文件名
        String originalFilename = file.getOriginalFilename();
        String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));//suffix后缀
        //使用uuid重新生成文件名，防止文件名称重复而造成文件覆盖
        String fileName= UUID.randomUUID().toString()+suffix;//随机random

        //创建一个目录对象
        File dir=new File(basePath);
        //判断当前对象是否存在
        if(!dir.exists()){
            //目录不存在，需要创建
            dir.mkdirs();
        }

        try {
            //将临时文件转存到指定位置
            file.transferTo(new File(basePath+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success(fileName);
    }
}
