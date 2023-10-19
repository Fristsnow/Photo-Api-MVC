package com.photo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;

    private String fullName;

    private String phoneNumber;

    private String shippingAddress;

    private String cardNumber;

    private String nameOnCard;

    private LocalDate expDate;

    private String cvv;

    private Double total;

    private List<Photo> photos;

//    private List<Integer> photo_id_list;

    private LocalDateTime orderPlace;

    private String status;

    private Integer userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
