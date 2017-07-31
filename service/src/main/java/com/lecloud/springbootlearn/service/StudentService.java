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
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.net.URI;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpMethod.GET;

@Service
public class StudentService {

    @Autowired
    public StudentDAO studentDAO;

    public List<StudentEntity> studentMapperTest() {
        return studentDAO.getStudentAll();
    }

}

