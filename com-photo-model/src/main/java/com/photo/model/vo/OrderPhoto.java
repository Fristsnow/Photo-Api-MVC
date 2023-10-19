package com.photo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPhoto {
    private Integer id;

    private String fullName;

    private String phoneNumber;

    private String shippingAddress;

    private String cardNumber;

    private String nameOnCard;

    private LocalDate expDate;

    private String cvv;

    private Double total = 0.0;

    private LocalDateTime orderPlace;

    private String status;

    private Integer userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
