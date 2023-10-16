package com.photo.service.impl;

import com.photo.mapper.SizeMapper;
import com.photo.model.Size;
import com.photo.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeMapper sizeMapper;
    @Override
    public List<Size> list() {
        return sizeMapper.list();
    }

    @Override
    public void updateSize(Size size) {
        sizeMapper.updateSize(size);
    }

    @Override
    public List<Size> sizeById(Integer id) {
        return sizeMapper.sizeById(id);
    }


}
