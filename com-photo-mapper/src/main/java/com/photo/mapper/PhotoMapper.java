package com.photo.mapper;

import com.photo.model.Photo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotoMapper {
    List<Photo> photoList();

    void CreatePhoto(String newFileName, Integer sizeId,Integer userId);

    void deletePhoto(Integer id);

    Photo photoBySizeId(Integer id);

    void updatePhoto(String newFileName, Integer photoId, Integer frameId);

    Photo photoById(Integer photoId);

    Photo photoByUserId(Integer userId);

    void updateCart(Integer id);

    void photoByOrderId(Integer id);

    List<Photo> photoByIdL(Integer clientId);
}
