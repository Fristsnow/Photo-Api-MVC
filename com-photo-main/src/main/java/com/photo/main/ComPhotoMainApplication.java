package com.photo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.photo.*")
public class ComPhotoMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComPhotoMainApplication.class, args);
    }

}
