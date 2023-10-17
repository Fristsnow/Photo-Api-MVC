package com.photo.service.impl;

import com.photo.mapper.PhotoMapper;
import com.photo.model.Photo;
import com.photo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public List<Photo> photoList() {
        return photoMapper.photoList();
    }

    /**
     * upload Photo
     *
     * @param newFileName
     * @param sizeId
     * @return
     */
    @Override
    public void CraetePhoto(String newFileName, Integer sizeId, Integer userId) {
        photoMapper.CreatePhoto(newFileName, sizeId, userId);
    }

    @Override
    public void deletePhoto(Integer id) {
        photoMapper.deletePhoto(id);
    }

    /**
     * size photo select
     *
     * @param id
     * @return
     */
    @Override
    public Photo photoBySizeId(Integer id) {
        return photoMapper.photoBySizeId(id);
    }

    /**
     * update frame_url frame_name
     * @param newFileName
     * @param photoId
     * @param frameId
     */
    @Override
    public void updatePhoto(String newFileName, Integer photoId, Integer frameId) {
        photoMapper.updatePhoto(newFileName, photoId, frameId);
    }

    @Override
    public Photo photoById(Integer photoId) {
        return photoMapper.photoById(photoId);
    }

    @Override
    public Photo photoByUserId(Integer userId) {
        return photoMapper.photoByUserId(userId);
    }

    @Override
    public void updateCart(Integer id) {
        photoMapper.updateCart(id);
    }

    @Override
    public void photoByOrderId(Integer id) {
        photoMapper.photoByOrderId(id);
    }

    @Override
    public List<Photo> photoByUserIdL(Integer clientId) {
        return photoMapper.photoByIdL(clientId);
    }
}
