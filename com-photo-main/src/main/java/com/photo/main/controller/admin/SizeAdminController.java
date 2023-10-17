package com.photo.main.controller.admin;

import com.photo.common.utils.Result;
import com.photo.model.Size;
import com.photo.service.SizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Size
 */
@RestController
@Slf4j
@RequestMapping("/admin/size")
public class SizeAdminController {

    @Autowired
    private SizeService sizeService;

    /**
     * select
     * @return
     */
    @GetMapping
    public Result<List<Size>> list(HttpServletRequest request){
        if (request.getSession().getAttribute("admin") != null) {
            log.info("所有Size");
            List<Size> size = sizeService.list();
            return Result.success(size);
        }
        return Result.error(401,"unauthenticated");
    }

    @PatchMapping
    public Result<List<Size>> updateSize(HttpServletRequest request,@RequestBody Size size){
        if (request.getSession().getAttribute("admin") != null) {
            log.info("查找Size,{}", size);
            sizeService.updateSize(size);
            List<Size> sizeList = sizeService.sizeById(size.getId());
            return Result.success(sizeList);
        }
        return Result.error(401,"unauthenticated");
    }
}
