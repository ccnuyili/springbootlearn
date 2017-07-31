package com.lecloud.springbootlearn.service;

import com.lecloud.springbootlearn.cons.MyConst;
import com.lecloud.springbootlearn.util.MyUtil;
import com.lecloud.springbootlearn.vo.DomainQueryResultVO;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class RestTemplateService {
    @Value("${domain.url}")
    private String domainUrl;

    @Value("${url}")
    private String myUrl;

    public DomainQueryResultVO accessUrlbyRestTemplate() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        DomainQueryResultVO domainQueryResult = new DomainQueryResultVO();
        ResponseEntity<String> response = restTemplate.getForEntity(domainUrl, String.class);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            String responseString = response.getBody();
            HttpHeaders headers = response.getHeaders();
            System.out.println(headers.toString());
            //反序列化：String -> Object
            domainQueryResult = MyUtil.OBJECT_MAPPER.readValue(responseString, DomainQueryResultVO.class);
            //序列化: Object -> String
            String serializationResult = MyUtil.OBJECT_MAPPER.writeValueAsString(domainQueryResult);
            System.out.println(serializationResult);
        }
        return domainQueryResult;
    }

    public void accessUrlByCloseableHttpClient() throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(1000).build();
        HttpGet httpGet = new HttpGet(myUrl);
        httpGet.setConfig(requestConfig);
        httpGet.setHeader(new BasicHeader(MyConst.HEADER_HOST.getKey(), MyConst.HEADER_HOST.getValue()));
        CloseableHttpResponse response = httpclient.execute(httpGet);
        int result;
        if (response.getStatusLine().getStatusCode() == 200) {
            //EntityUtils只能调用一次
            result = Integer.parseInt(EntityUtils.toString(response.getEntity()).trim());
        }
    }

    public void accessUrlbyRestTemplateExchange() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        URI uri=new URI(myUrl);
        HttpHeaders header = new HttpHeaders();
        //header.setAccept(singletonList(MediaType.valueOf("application/json")));
        header.set("yiliheaderkey", MyConst.HEADER_HOST.getValue());
        header.set("Authorization","Basic MTIzNDU6ODFhOWI1M2IwZmNlYWIzODVmMTM1ZTdjZDgyMWQzMWI0Njg5MTdmN2YwNWMzMGMwNmIyNGJlMzMzM2ZlNDAxOQ==");

        //经测试，以下方式正常情况是可行的，服务端能接收到header信息。但不知为何请求不了回源并发数接口。
        RequestEntity<String> requestEntity = new RequestEntity<String>(header, HttpMethod.GET,uri);
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            String responseString = response.getBody();
        }
    }

}
