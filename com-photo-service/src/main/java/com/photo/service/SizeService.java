package com.photo.service;

import com.photo.model.Size;

import java.util.List;

public interface SizeService {

    List<Size> list();


    void updateSize(Size size);

    List<Size> sizeById(Integer id);
}
