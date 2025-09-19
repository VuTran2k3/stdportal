package com.example.stdportal.DTO;

import com.example.stdportal.Model.Classroom;
import com.example.stdportal.Model.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class StudentDTO {
    private String name;
//    private Classroom classroom;
    private Date dob;
    private Double gpa;

    public Student toStudent(){
        Student student = new Student();
        student.setName(name);
//        student.setClassroom(classroom);
        student.setDob(dob);
        student.setGpa(gpa);
        return student;
    }

    public void updateToStudent(Student student){
        student.setName(name);
//        student.setClassroom(classroom);
        student.setDob(dob);
        student.setGpa(gpa);
    }
}
