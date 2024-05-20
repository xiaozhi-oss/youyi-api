package com.youyi.api.controller;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.youyi.api.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaozhi
 */
@RestController
@RequestMapping("/youyi/upload")
public class UploadController {
    public final String BASE_URL = "http://localhost:8888/youyi/upload/readFile/";
    
    @Value("${upload.save.path}")
    public String savePath;
    
    @PostMapping()
    public ResponseResult<List<String>> upload(
            @RequestPart("imgList") MultipartFile[] fileList) throws IOException {
        List<String> imgUrls = new ArrayList<>();
        for (MultipartFile file : fileList) {
            Digester md5 = new Digester(DigestAlgorithm.MD5);
            String hex = md5.digestHex(file.getInputStream());
            String filename = file.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf("."));
            file.transferTo(new File(savePath + hex + suffix));
            imgUrls.add(BASE_URL + hex + suffix);
        }
        return ResponseResult.success(imgUrls);
    }

    @GetMapping("/readFile/{filename}")
    public ResponseResult<File> getFile(
            @PathVariable("filename") String filename,
            HttpServletResponse response) throws IOException {
        filename = savePath + filename;
        OutputStream os = response.getOutputStream();
        os.write(Files.readAllBytes(Paths.get(filename)));
        os.close();
        return ResponseResult.success();
    }
}
