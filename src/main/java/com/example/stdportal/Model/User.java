package com.example.stdportal.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// User.java
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name="user", uniqueConstraints=@UniqueConstraint(columnNames="username"))
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=64)
    private String username;
    @Column(nullable=false, length=64)
    private String password;
    @Enumerated(EnumType.STRING) @Column(nullable=false)
    private Role role;

    private boolean enabled = true;

    public enum Role { STUDENT, ADMIN }
}

