package com.photo.main.controller.admin;

import com.photo.common.Result;
import com.photo.model.Size;
import com.photo.service.SizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result<List<Size>> list(){
        log.info("所有Size");
        List<Size> size = sizeService.list();
        return Result.success(size);
    }

    @PatchMapping
    public Result<List<Size>> updateSize(@RequestBody Size size){
        log.info("查找Size,{}",size);
        sizeService.updateSize(size);
        List<Size> sizeList = sizeService.sizeById(size.getId());
        return Result.success(sizeList);
    }
}
