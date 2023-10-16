package com.photo.service.impl;

import com.photo.mapper.FrameMapper;
import com.photo.model.Frame;
import com.photo.service.FrameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrameServiceImpl implements FrameService {

    @Autowired
    private FrameMapper frameMapper;
    @Override
    public List<Frame> frameList() {
        return frameMapper.frameList();
    }

    @Override
    public void updateFrame(Frame frame) {
        frameMapper.updateFrame(frame);
    }

    @Override
    public List<Frame> frameById(Integer id) {
        return frameMapper.frameById(id);
    }
}
