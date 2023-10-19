package com.photo.service;


import com.photo.model.Photo;

import java.util.List;

public interface PhotoService {
    List<Photo> photoList();

    void CraetePhoto(String newFileName, Integer sizeId,Integer userId);

    void deletePhoto(Integer id);

    Photo photoBySizeId(Integer id);

    void updatePhoto(String newFileName, Integer photoId, Integer frameId);

    Photo photoById(Integer photoId);

    /**
     * Cart Select
     * @param userId
     * @return
     */
    List<Photo> photoByUserId(Integer userId);

    void updateCart(Integer id);

    void photoByOrderId(Integer id,Integer orderId);

    List<Photo> photoByUserIdL(Integer clientId);

    List<Photo> photoUserIdOrderId(Integer clientId, Integer oId);

    void updateValid(Integer id);
}
