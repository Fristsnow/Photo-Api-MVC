package com.photo.main.controller.client;

import com.photo.common.utils.Result;
import com.photo.model.Photo;
import com.photo.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/client/photo")
public class PhotoClientController {

    @Autowired
    private PhotoService photoService;

    /**
     * Select Photo
     * @param request
     * @return
     */
    @GetMapping
    public Result<List<Photo>> photoList(HttpServletRequest request) {
        if (request.getSession().getAttribute("client") != null) {
            log.info("Client Photo List");
            List<Photo> photos = photoService.photoList();
            return Result.success(photos);
        }
        return Result.error(401, "unauthenticated");
    }

    /**
     * Photo upload
     *
     * @param request
     * @param file
     * @param id
     * @return
     */
    @PostMapping("/{id}")
    public Result<String> uploadPhoto(HttpServletRequest request, MultipartFile file, @PathVariable Integer id) {
        if (request.getSession().getAttribute("client") != null) {
//            if (file.isEmpty()) {
//                return Result.error(422, "upload error");
//            }
            String filename = file.getOriginalFilename();
            String ext = "." + filename.split("\\.")[1];
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String newFileName = uuid + ext;
            Integer userId = (Integer) request.getSession().getAttribute("client_id");
            photoService.CraetePhoto(newFileName, id, userId);
            ApplicationHome home = new ApplicationHome(this.getClass());
            String pre = home.getDir().getParentFile().getParentFile().getAbsoluteFile() +
                    "\\src\\main\\resources\\static\\images\\";
            String path = pre + newFileName;
            try {
                file.transferTo(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Result.success(path);
        }
        return Result.error(401, "unauthenticated");
    }

    /**
     * Delete photo
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<String> deletePhoto(@PathVariable Integer id){
        photoService.deletePhoto(id);
        return Result.success();
    }
}
