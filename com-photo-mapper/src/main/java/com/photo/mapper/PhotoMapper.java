package com.photo.mapper;

import com.photo.model.Photo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotoMapper {
    List<Photo> photoList();
}
