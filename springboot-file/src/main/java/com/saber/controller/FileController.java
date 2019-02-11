package com.saber.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@RestController
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }
        String fileName = file.getOriginalFilename();
        LOGGER.info("文件名：" + fileName);
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        LOGGER.info("后缀名：" + suffixName);
        // 设置文件存储位置
        String filePath = "D:/Users/Saber/Downloads/";
        String path = filePath + fileName;
        File dest = new File(path);
        // 检查是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();// 新建文件夹
        }
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @PostMapping("/batch")
    public String batch(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String filePath = "D:/Users/Saber/Downloads/";
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + file.getOriginalFilename())));// 设置文件路径及名字
                    stream.write(bytes);
                    stream.close();
                } catch (IOException e) {
                    stream = null;
                    return "第" + i + "个文件上传失败，" + e.getMessage();
                }
            } else {
                return "第" + i + "个文件上传失败，因为为空";
            }
        }
        return "上传成功";
    }

    @GetMapping("/download")
    public String download(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "说明.txt";// 文件名
        if (fileName != null) {
            File file = new File("D:/Users/Saber/Downloads/" + fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                try {
                    //解决IE11和谷歌浏览器下载文件名称显示编码不正确的情况
                    String agent = request.getHeader("User-Agent").toUpperCase();
                    if (agent.indexOf("MSIE") > 0 || agent.indexOf("GECKO") > 0 && agent.indexOf("RV:11") > 0) {
                        response.setHeader("content-disposition", "attachment;filename="
                                + URLEncoder.encode(fileName, "UTF-8"));
                    } else {
                        response.setHeader("content-disposition", "attachment;filename="
                                + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;
                try {
                    fis = new FileInputStream(file);
                    OutputStream os = response.getOutputStream();
                    bis = new BufferedInputStream(fis);
                    bos = new BufferedOutputStream(os);
                    int i = bis.read(buffer);
                    while (i != -1) {
                        bos.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    os.flush();
                    return "下载成功";
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (bos != null) {
                        try {
                            bos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }
}
