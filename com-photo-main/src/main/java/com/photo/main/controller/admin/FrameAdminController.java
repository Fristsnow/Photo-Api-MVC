package com.photo.main.controller.admin;

import com.photo.common.Result;
import com.photo.model.Frame;
import com.photo.service.FrameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/frame")
public class FrameAdminController {

    @Autowired
    private FrameService frameService;

    @GetMapping
    public Result<List<Frame>> frameList(){
        log.info("Frame查询所有");
        List<Frame> frameList =  frameService.frameList();
        return Result.success(frameList);
    }

    @PatchMapping
    public Result<List<Frame>> updateFrame(@RequestBody Frame frame){
        log.info("Frame修改：{}",frame);
        frameService.updateFrame(frame);
        List<Frame> frameList = frameService.frameById(frame.getId());
        return Result.success(frameList);
    }
}
