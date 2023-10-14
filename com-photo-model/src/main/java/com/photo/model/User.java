package com.photo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    private String email;

    private String fullName;

    private String username;

    private String password;

    private String token;

    private Integer isAdmin;

    private LocalDate createTime;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
