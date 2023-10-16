package com.photo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Frame {

    private Integer id;

    private String url;

    private Double price;

    private String name;

    private String frameName;

    private String size;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
