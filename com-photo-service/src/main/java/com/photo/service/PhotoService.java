package com.photo.service;


import com.photo.model.Photo;

import java.util.List;

public interface PhotoService {
    List<Photo> photoList();

    void CraetePhoto(String newFileName, Integer sizeId,Integer userId);

    void deletePhoto(Integer id);
}
