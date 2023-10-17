package com.photo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;

    private String fullName;

    private Integer phoneNumber;

    private String shippingAddress;

    private String cardNumber;

    private String nameOnCard;

    private LocalDate expDate;

    private Integer cvv;

    private Integer total;

    private List<Photo> photos;

    private LocalDateTime orderPlace;

    private String status;

    private Integer userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
