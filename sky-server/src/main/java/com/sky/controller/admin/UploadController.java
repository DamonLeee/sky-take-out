package com.sky.controller.admin;


import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
@Api(tags = "通用接口")
@RequestMapping("/admin/common")
public class UploadController {

    private final static String FILE_UPLOAD_PATH = "D:\\A WorkingSpace\\images\\";

    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        log.info("文件上传: {}",file);
        //获取原始文件名 - 1.jpg  123.0.0.jpg
        String originalFilename = file.getOriginalFilename();

        //构造唯一的文件名 (不能重复) - uuid(通用唯一识别码) de49685b-61c0-4b11-80fa-c71e95924018
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新的文件名: {}", newFileName);

        //将文件存储在本地的磁盘目录中 D:\A WorkingSpace\images
        file.transferTo(new File("D:\\A WorkingSpace\\images\\"+newFileName));
        System.out.println(FILE_UPLOAD_PATH + newFileName);

        return Result.success(FILE_UPLOAD_PATH + newFileName);
    }
}
