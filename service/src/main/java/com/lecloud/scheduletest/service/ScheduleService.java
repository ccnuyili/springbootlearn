package com.lecloud.scheduletest.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleService {
    @Scheduled(fixedRate = 1000 * 2,initialDelay = 0)
    public void show() {
        System.out.println(" task is running!");
    }
}