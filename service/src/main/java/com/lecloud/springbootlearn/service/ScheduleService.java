package com.lecloud.springbootlearn.service;

import com.lecloud.springbootlearn.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ScheduleService {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    EmailUtil emailUtil;

    int count = 0;

    /*
    * 定时启动某个方法，所以多个定时任务之间可以类的成员变量相联系。
    * */
    @Scheduled(fixedRate = 1000 * 60 * 20, initialDelay = 0)
    public void show() throws Exception {
        int emailNum = 10;
        System.out.println("监测次数：" + (String.valueOf(count++)) + "   1430待预定。。。");
        String url = "http://www.ziroom.com/z/vr/60067485.html"; //1430
        try {
            ResponseEntity<String> responseEntity = restTemplate
                    .exchange(url, HttpMethod.GET, null, String.class);
            if (!responseEntity.getBody().contains("buding-loading.jpg") && emailNum -- > 0) {
                emailUtil.sendMail("自如抢房", "赶快预定\n" + url);
            }
        } catch (Exception e) {
            if( emailNum -- > 0)
                emailUtil.sendMail("自如抢房", "查看能否预定\n" + url);
        }
    }

    @Scheduled(fixedRate = 1000 * 60, initialDelay = 0)
    public void chaoyangxincheng() throws Exception {  //1500以下3居室
        String cyxcUrl = "http://www.ziroom.com/z/nl/r1-z3.html?qwd=朝阳新城";
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(cyxcUrl, HttpMethod.GET, null, String.class);
        if (responseEntity.getBody().contains("3居室")) {
            emailUtil.sendMail("朝阳新城房源1500以下3居室", cyxcUrl);
        }
    }

    @Scheduled(fixedRate = 1000 * 60, initialDelay = 0)
    public void chaoyangxincheng2() throws Exception { //1500~1700
        String cyxcUrl = "http://www.ziroom.com/z/nl/r2-z3.html?qwd=朝阳新城";
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(cyxcUrl, HttpMethod.GET, null, String.class);
        if (responseEntity.getBody().contains("￥ 15") || responseEntity.getBody().contains("￥ 16")) {
            emailUtil.sendMail("朝阳新城房源1500~1699", cyxcUrl);
        }
    }
}