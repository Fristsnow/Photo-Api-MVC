package com.photo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Size {

    private Integer id;

    private String size;

    private Double width;

    private Double height;

    private Double price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
