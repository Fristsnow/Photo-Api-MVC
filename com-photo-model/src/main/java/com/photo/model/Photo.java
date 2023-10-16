package com.photo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    private Integer id;

    private String editedUrl;

    private String originalUrl;

    private String framedUrl;

    private String frameName;

    private String size;

    private Double printPrice;

    private Double framePrice;

    private String status;

    private Integer frameId;

    private Integer userId;

    private Integer sizeId;

    private Integer orderId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
