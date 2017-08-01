package com.lecloud.springbootlearn.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class ScheduleService {

    /*
    * 定时启动某个方法，所以多个定时任务之间可以类的成员变量相联系。
    * */
    @Scheduled(fixedRate = 1000 * 2,initialDelay = 0)
    public void show() {
        System.out.println(" ScheduleService is running!");
    }
}