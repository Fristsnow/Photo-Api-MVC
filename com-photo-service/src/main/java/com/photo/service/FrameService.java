package com.photo.service;

import com.photo.model.Frame;

import java.util.List;

public interface FrameService {
    List<Frame> frameList();

    void updateFrame(Frame frame);

    List<Frame> frameById(Integer id);
}
