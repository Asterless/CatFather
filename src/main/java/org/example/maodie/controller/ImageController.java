package org.example.maodie.controller;

import org.example.maodie.model.Image_maodie;
import org.example.maodie.mapper.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    // 获取所有图片 - 添加分页支持
    @GetMapping
    public ResponseEntity<List<Image_maodie>> getAllImages() {
        List<Image_maodie> images = imageRepository.findAll();
        return ResponseEntity.ok(images);
    }

    // 根据ID查询 - 路径变量名改为小写
    @GetMapping("/{id}")
    public ResponseEntity<Image_maodie> getImageById(@PathVariable Long id) {
        return imageRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 根据文件名查询
    @GetMapping("/by-filename/{filename}")
    public ResponseEntity<Image_maodie> getImageByFilename(
            @PathVariable String filename) {
        return imageRepository.findByFilename(filename)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 根据文件路径查询
    @GetMapping("/by-filepath/{filePath}")
    public ResponseEntity<Image_maodie> getImageByFilePath(
            @PathVariable String filePath) {
        return imageRepository.findByFilePath(filePath)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 根据上传时间查询 - 改为请求参数方式更合适
    @GetMapping("/by-upload-time")
    public ResponseEntity<Image_maodie> getImageByUploadTime(
            @RequestParam LocalDateTime uploadTime) {
        return imageRepository.findByUploadTime(uploadTime)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 新增：时间范围查询
    @GetMapping("/by-time-range")
    public ResponseEntity<List<Image_maodie>> getImagesByTimeRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        List<Image_maodie> images = imageRepository.findByUploadTimeBetween(start, end);
        return ResponseEntity.ok(images);
    }
}