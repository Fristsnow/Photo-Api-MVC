package com.photo.main.controller.client;

import com.photo.common.utils.Result;
import com.photo.model.Frame;
import com.photo.service.FrameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Client Frame
 */
@RestController
@RequestMapping("/client/frame")
@Slf4j
public class FrameClientController {

    @Autowired
    private FrameService frameService;

    @GetMapping
    public Result<List<Frame>> frameList(HttpServletRequest request){
        if (request.getSession().getAttribute("client") != null) {
            List<Frame> frameList = frameService.frameList();
            return Result.success(frameList);
        }
        return Result.error(401,"unauthenticated");
    }
}
