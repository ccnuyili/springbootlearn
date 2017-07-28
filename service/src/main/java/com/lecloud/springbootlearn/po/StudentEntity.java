package com.lecloud.springbootlearn.po;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//implements Serializable表示可以序列化
//PO: persistant object持久对象：
// 最形象的理解就是PO对象的属性与数据库中表的字段相对应（通过ResultMap指定），一个PO实例就是数据库中的一条记录。

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity implements Serializable {

    private String studentID;
    private String studentName;
    private String studentSex;
    private Date studentBirthday;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public Date getStudentBirthday() {
        return studentBirthday;
    }

    public void setStudentBirthday(Date studentBirthday) {
        this.studentBirthday = studentBirthday;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "studentID='" + studentID + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentSex='" + studentSex + '\'' +
                ", studentBirthday=" + studentBirthday +
                '}';
    }
}

