package com.photo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"createdTime,updatedTime"})
public class User {

    private Integer id;

    private String email;

    private String fullName;

    private String username;

    private String password;

    private String token;

    private Integer isAdmin;

    private LocalDateTime createTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String repeatPassword;

//    public void setIsAdmin(boolean b) {
//    }
}
