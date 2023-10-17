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
import java.util.HashMap;
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
     *
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
        return Result.error401();
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
    public Result<String> uploadPhoto(HttpServletRequest request, @PathVariable Integer id, MultipartFile file) {
        if (request.getSession().getAttribute("client") != null) {
            if (file.isEmpty()) return Result.error(422, "upload error");
            String filename = file.getOriginalFilename();
            String ext = "." + filename.split("\\.")[1];
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String newFileName = uuid + ext;
            Integer userId = (Integer) request.getSession().getAttribute("client_id");
            photoService.CraetePhoto(newFileName, id, userId);

            String path = getFileMontage(file, newFileName);

            return Result.success(path);
        }
        return Result.error401();
    }

    /**
     * Delete photo
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<String> deletePhoto(HttpServletRequest request, @PathVariable Integer id) {
        if (request.getSession().getAttribute("client") != null) {
            photoService.deletePhoto(id);
            return Result.success();
        }
        return Result.error401();

    }

    /**
     * Select Size_id
     *
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<HashMap<String, Object>> SizePhoto(HttpServletRequest request, @PathVariable Integer id) {
        if (request.getSession().getAttribute("client") != null) {
            Photo photo = photoService.photoBySizeId(id);
            if (photo == null) return Result.error(404, "not found");
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", photo.getId());
            map.put("edited_url", photo.getEditedUrl());
            map.put("original_url", photo.getOriginalUrl());
            map.put("framed_url", photo.getFramedUrl());
            map.put("status", photo.getStatus());
            return Result.success(map);
        }
        return Result.error401();
    }

    /**
     * Add framed_url
     *
     * @param request
     * @param file
     * @param photo_id
     * @param frame_id
     * @return
     */
    @PostMapping("/{photo_id}{frame_id}")
    public Result<HashMap<String, Object>> FramePhoto(HttpServletRequest request, MultipartFile file, @PathVariable Integer photo_id, @PathVariable Integer frame_id) {
        if (request.getSession().getAttribute("client") != null) {
            if (file.isEmpty()) return Result.error(422, "upload error");
            String filename = file.getOriginalFilename();
            String ext = "." + filename.split("\\.")[1];
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String newFileName = uuid + ext;

            photoService.updatePhoto(newFileName, photo_id, frame_id);
            Photo photo = photoService.photoById(photo_id);

            String path = getFileMontage(file, newFileName);


            HashMap<String, Object> map = new HashMap<>();
            map.put("id", photo.getId());
            map.put("framed_url", photo.getFramedUrl());
            map.put("path", path);
            return Result.success(map);

        }
        return Result.error401();

    }

    /**
     * 路径拼接
     *
     * @param file
     * @param newFileName
     * @return
     */
    private String getFileMontage(MultipartFile file, String newFileName) {
        ApplicationHome home = new ApplicationHome(this.getClass());
        String pre = home.getDir().getParentFile().getParentFile().getAbsoluteFile() +
                "\\src\\main\\resources\\static\\images\\";
        String path = pre + newFileName;
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

}
