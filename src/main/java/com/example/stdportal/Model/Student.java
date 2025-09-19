package com.example.stdportal.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Getter @Setter @ToString @NoArgsConstructor
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String major;
    private Date dob;
    private Double gpa;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "classroom_name")
//    private Classroom classroom;

    private String meta;// = this.id + String.join("-",name.split(" "));
    @CreatedDate
    private LocalDateTime createdDate;
}
