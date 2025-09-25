package com.example.stdportal.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "teacher")
@Entity @Getter @Setter @NoArgsConstructor
public class Teacher {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    private String lastname;
    private String role;

    @Email @Column(unique = true)
    private String email;

//    @OneToMany(mappedBy = "homeroomTeacher", cascade = CascadeType.ALL)
//    private List<Classroom> classrooms = new ArrayList<>();

    @Column(unique = true)
    public String meta;

    @CreatedDate
    private LocalDateTime createdDate;

}
