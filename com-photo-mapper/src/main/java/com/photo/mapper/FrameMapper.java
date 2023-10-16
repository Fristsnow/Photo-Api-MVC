package com.photo.mapper;

import com.photo.model.Frame;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FrameMapper {
    List<Frame> frameList();

    List<Frame> frameById(Integer id);

    void updateFrame(Frame frame);
}
