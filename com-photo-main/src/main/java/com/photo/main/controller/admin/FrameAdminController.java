package com.photo.main.controller.admin;

import com.photo.common.utils.Result;
import com.photo.model.Frame;
import com.photo.service.FrameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Frame
 */
@RestController
@Slf4j
@RequestMapping("/admin/frame")
public class FrameAdminController {

    @Autowired
    private FrameService frameService;

    @GetMapping
    public Result<List<Frame>> frameList(HttpServletRequest request) {
        if (request.getSession().getAttribute("admin") != null) {

            log.info("Frame查询所有");
            List<Frame> frameList = frameService.frameList();
            return Result.success(frameList);
        }
        return Result.error(401, "unauthenticated");

    }

    @PatchMapping
    public Result<List<Frame>> updateFrame(HttpServletRequest request, @RequestBody Frame frame) {
        if (request.getSession().getAttribute("admin") != null) {

            log.info("Frame修改：{}", frame);
            frameService.updateFrame(frame);
            List<Frame> frameList = frameService.frameById(frame.getId());
            return Result.success(frameList);
        }
        return Result.error(401, "unauthenticated");
    }
}
