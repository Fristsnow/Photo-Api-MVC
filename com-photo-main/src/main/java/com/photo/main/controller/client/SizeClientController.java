package com.photo.main.controller.client;

import com.photo.common.utils.Result;
import com.photo.model.Size;
import com.photo.service.SizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Client Size
 */
@RestController
@Slf4j
@RequestMapping("/client/size")
public class SizeClientController {

    @Autowired
    private SizeService sizeService;

    @GetMapping
    public Result<List<Size>> listSize(HttpServletRequest request) {
        if (request.getSession().getAttribute("client") != null) {
            log.info("Client Size");
            List<Size> list = sizeService.list();
            return Result.success(list);
        }
        return Result.error(401, "unauthenticated");
    }
}
