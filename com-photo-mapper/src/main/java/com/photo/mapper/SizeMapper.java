package com.photo.mapper;

import com.photo.model.Size;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SizeMapper {
    List<Size> list();

    void updateSize(Size size);

    List<Size> sizeById(Integer id);
}
