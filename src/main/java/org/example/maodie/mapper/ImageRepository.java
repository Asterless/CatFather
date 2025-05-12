package org.example.maodie.mapper;

import org.example.maodie.model.Image_maodie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image_maodie, Long> {

    // 1. 简单查询方法 - 无需@Query
    Optional<Image_maodie> findByFilename(String filename);

    Optional<Image_maodie> findByFilePath(String filePath);

    // 2. 精确时间查询
    Optional<Image_maodie> findByUploadTime(LocalDateTime uploadTime);

    // 3. 模糊查询
    List<Image_maodie> findByFilenameContaining(String keyword);

    List<Image_maodie> findByFilePathContaining(String keyword);

    // 4. 时间范围查询
    List<Image_maodie> findByUploadTimeBetween(LocalDateTime start, LocalDateTime end);

    // 5. 自定义JPQL查询
    @Query("SELECT i FROM Image_maodie i WHERE LOWER(i.filename) LIKE LOWER(concat('%', :keyword, '%'))")
    List<Image_maodie> searchByFilename(@Param("keyword") String keyword);

    // 6. 原生SQL查询示例
    @Query(value = "SELECT * FROM image_maodie WHERE file_path LIKE %:path%", nativeQuery = true)
    List<Image_maodie> searchByFilePathNative(@Param("path") String path);

    // 7. 统计查询
    long countByUploadTimeAfter(LocalDateTime date);

    // 8. 存在性检查
    boolean existsByFilename(String filename);
}