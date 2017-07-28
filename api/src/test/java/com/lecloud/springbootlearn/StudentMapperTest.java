package com.lecloud.springbootlearn;

import com.lecloud.springbootlearn.dao.StudentDAO;
import com.lecloud.springbootlearn.po.StudentEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
//指定启动哪个main类：
@SpringBootTest("ApiApplication.class")
public class StudentMapperTest {

    @Autowired
    public StudentDAO studentDAO;

    @Test
    public void studentMapperTest(){
        List<StudentEntity> studentList = studentDAO.getStudentAll();
        for( StudentEntity entityTemp : studentList){
            System.out.println(entityTemp.toString());
        }
        System.out.println("test studentMapperTest success");
    }
}
