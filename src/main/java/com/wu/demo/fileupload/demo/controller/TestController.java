package com.wu.demo.fileupload.demo.controller;

import com.wu.demo.fileupload.demo.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class TestController {

    private final ResourceLoader resourceLoader;

    @Autowired
    public TestController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${web.upload-path}")
    private String path;

    /**
     * 跳转到文件上传页面
     * @return
     */
    @RequestMapping("test")
    public String toUpload(){
        return "test";
    }

    /**
     *
     * @param file 要上传的文件
     * @return
     */
    @RequestMapping("fileUpload")
    public String upload(@RequestParam("fileName") MultipartFile file, Map<String,Object> model){

        // 要上传的目标文件存放路径
        String localPath = "E:/Develop/Files/Photos";
        // 上传成功或者失败的提示
        String msg = "";

        if (FileUtils.upload(file, localPath, file.getOriginalFilename())){
            // 上传成功，跳转到页面
            msg = "上传成功！";
        }else {
            msg = "上传失败！";

        }

        // 显示图片
        model.put("msg", msg);
        model.put("fileName", file.getOriginalFilename());

        return "forward:/test";
    }

    /**
     * 显示单张图片
     * @return
     */
    @RequestMapping("show")
    public ResponseEntity showPhotos(HttpServletResponse response, String fileName){
        // 这个请求是img中的src写的，直接请求会是乱码
        response.setContentType("image/jpg");

        try {
            //return ResponseEntity.ok(resourceLoader.getResource("file:" + path +"a_copy.jpg"));
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + fileName));
            //return ResponseEntity.ok(path);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
