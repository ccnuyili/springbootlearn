package com.lecloud.springbootlearn.service;

import com.lecloud.springbootlearn.cons.MyConst;
import com.lecloud.springbootlearn.dao.StudentDAO;
import com.lecloud.springbootlearn.po.StudentEntity;
import com.lecloud.springbootlearn.util.MyUtil;
import com.lecloud.springbootlearn.vo.DomainQueryResultVO;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class MyService {

    @Autowired
    public StudentDAO studentDAO;

    @Value("${domain.url}")
    private String domainUrl;

    public List<StudentEntity> studentMapperTest() {
        return studentDAO.getStudentAll();
    }

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
        HttpGet httpGet = new HttpGet(domainUrl);
        httpGet.setConfig(requestConfig);
        httpGet.setHeader(new BasicHeader(MyConst.HEADER_HOST.getKey(), MyConst.HEADER_HOST.getValue()));
        CloseableHttpResponse response = httpclient.execute(httpGet);
        int result;
        if (response.getStatusLine().getStatusCode() == 200) {
            //EntityUtils只能调用一次
            result = Integer.parseInt(EntityUtils.toString(response.getEntity()).trim());
        }
    }
}

