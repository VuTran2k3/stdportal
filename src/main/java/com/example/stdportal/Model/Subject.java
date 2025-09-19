package com.example.stdportal.Model;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class Subject {

    private String meta;
    @CreatedDate
    private LocalDateTime createdDate;
}
