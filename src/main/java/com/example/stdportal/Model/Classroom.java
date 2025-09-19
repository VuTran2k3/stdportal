package com.example.stdportal.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter @Setter @NoArgsConstructor
public class Classroom {
    @Id
    private String name;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "teacher_id")
//    private Teacher homeroomTeacher;

//    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Student> students = new ArrayList<>();

    private String meta;
    @CreatedDate
    private LocalDateTime createdDate;
}
