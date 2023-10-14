package com.photo.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.photo.*")
@MapperScan(value = "com.photo.mapper")
public class ComPhotoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComPhotoApplication.class, args);
    }

}
