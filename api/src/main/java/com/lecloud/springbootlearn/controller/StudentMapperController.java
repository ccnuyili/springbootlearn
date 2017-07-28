package com.lecloud.springbootlearn.controller;

import com.lecloud.springbootlearn.po.StudentEntity;
import com.lecloud.springbootlearn.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentMapperController {

    @Autowired
    MyService myService;

    @RequestMapping(path = "/test")
    public ResponseEntity StudentMapper() throws Exception {
        //mybatis
        List<StudentEntity> studentEntityList = myService.studentMapperTest();

        //设置响应报文头部
        HttpHeaders headers = new HttpHeaders();
        headers.add("a","b");

        myService.accessUrlbyRestTemplate();

        myService.accessUrlByCloseableHttpClient();

        /*
        1.ResponseEntity.ok()最终返回statusCode为OK的DefaultBuilder对象，该对象有2个字段：Object statusCode，HttpHeaders headers
        2.设置默认的headers，即new HttpHeaders()。
        3.调用.body()返回一个设置了statusCode，headersy以及body的ResponseEntity。
        */
        return (ResponseEntity.ok().headers(headers).body(studentEntityList));
    }
}
