package com.lecloud.springbootlearn.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @RequestMapping(path = "/test")
    public String  sds(){
        System.out.println(" api is running!");
        return "success";
    }
}
