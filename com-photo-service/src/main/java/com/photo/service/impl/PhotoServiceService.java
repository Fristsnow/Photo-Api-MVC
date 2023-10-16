package com.photo.service.impl;

import com.photo.mapper.PhotoMapper;
import com.photo.model.Photo;
import com.photo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceService implements PhotoService {

    @Autowired
    private PhotoMapper photoMapper;
    @Override
    public List<Photo> photoList() {
        return photoMapper.photoList();
    }
}
