package com.lecloud.springbootlearn.dao;

import com.lecloud.springbootlearn.po.StudentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


//MapStruct自动根据一个添加了@Mapper注解的接口生成一个实现类
//http://www.tianshouzhi.com/api/tutorials/mapstruct/292
@Mapper
public interface StudentDAO {

    public StudentEntity getStudent(String studentID);

//  public StudentEntity getStudentAndClass(String studentID);

    public List<StudentEntity> getStudentAll();

 /*   public void insertStudent(StudentEntity entity);

    public void deleteStudent(StudentEntity entity);

    public void updateStudent(StudentEntity entity);*/
}