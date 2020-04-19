package com.baize.framework.controller;

import com.baize.framework.dto.basic.ApiResponse;
import com.baize.framework.util.DownloadFileUtil;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author lubinjia
 * @create 2020/4/16 22:58
 */
//@Api(value = "示例api")
@Controller
public class UploadController {
    @GetMapping("/upload")
    public String index() {
        return "upload";
    }

    @GetMapping("/multiUpload")
    public String index2() {
        return "multiUpload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile srcFile) {
        if (srcFile.isEmpty()) {
            return "请选择一个文件";
        }
        try {
            //获取上传路径
            String uploadPath = getNewUploadPath(srcFile);
            //通过项目路径，拼接上传路径
            Path path = Paths.get(uploadPath);
            //根据srcFile大小，准备一个字节数组
            byte[] bytes = srcFile.getBytes();
            //开始将源文件写入目标地址
            Files.write(path, bytes);
            return "上传成功！文件路径：" + uploadPath;
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败！";
        }
    }

    @PostMapping("/upload2")
    @ResponseBody
    public String fileUpload2(@RequestParam("file2") MultipartFile file) throws FileNotFoundException {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        //获取上传路径
        String uploadPath = getUploadPathByTime(file);
        File uploadFile = new File(uploadPath);
        try {
            file.transferTo(uploadFile);
//            LOGGER.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
//            LOGGER.error(e.toString(), e);
            System.out.println(e.getMessage());
        }
        return "上传失败！";
    }

    @PostMapping("/multiUpload")
    @ResponseBody
    public String multiUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                return "上传第" + (i + 1) + "个文件失败";
            }
            try {
                //获取上传路径
                String uploadPath = getUploadPathByTime(file);
                File dest = new File(uploadPath);
                file.transferTo(dest);
//                LOGGER.info("第" + (i + 1) + "个文件上传成功");
            } catch (IOException e) {
//                LOGGER.error(e.toString(), e);
                return "上传第" + (i + 1) + "个文件失败";
            }
        }
        return "上传成功";
    }

    @PostMapping("/multiUpload2")
    @ResponseBody
    public String multiUpload2(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); i++) {
            file = files.get(i);
            if (!file.isEmpty()) {
                //获取上传路径
                try {
                    byte[] bytes = file.getBytes();
                    String uploadPath = getUploadPathByTime(file);
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(uploadPath)
                    ));
                    stream.write(bytes);
                    stream.close();
                } catch (FileNotFoundException e) {
                    stream = null;
                    return "上传第" + (i + 1) + "个文件失败";
                } catch (IOException e) {
                    stream = null;
                    return "上传第" + (i + 1) + "个文件失败";
                }
            } else {
                return "第" + (i + 1) + "个文件为空";
            }
        }
        return "上传成功";
    }

    @GetMapping("/download")
    @ResponseBody
    public String download(HttpServletResponse response) throws UnsupportedEncodingException {
//        String filename = "D:\\壁纸\\1561285199760.jpg";//文件名
        String filename = "D:\\导出铺货需求_20180721113600.xlsx";
        if (filename != null) {
            //设置文件路径
            File file = new File(filename);
            if (file.exists()) {
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition", "attachment;filename=" + filename);
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));

                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("Download  successfully!");
                    return "successfully";
                } catch (Exception e) {
                    System.out.println("Download  failed!");
                    return "failed";
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
                }
            }
        }
        return "successfully";
    }

    /**
     * 获取上传路径（根据时间拼接）
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    private String getNewUploadPath(MultipartFile file) throws FileNotFoundException {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 获得文件原始名称
        String fileName = file.getOriginalFilename();
        // 获得文件后缀名称
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        // 生成最新的uuid文件名称
        String newFileName = uuid + "." + suffixName;
        String uploadCatalog = getUploadCatalog();
        String path = uploadCatalog + "\\" + newFileName;
        System.out.println("文件名：" + path);
        return path;
    }

    /**
     * 获取上传路径（根据时间拼接）
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    private String getUploadPathByTime(MultipartFile file) throws FileNotFoundException {
        String uploadCatalog = getUploadCatalog();
        SimpleDateFormat sf_ = new SimpleDateFormat("yyyyMMddHHmmss");
        String times = sf_.format(new Date());
        String path = uploadCatalog + "\\" + times + "_" + file.getOriginalFilename();
        System.out.println("文件名：" + path);
        return path;
    }

    //获取上传文件目录
    private String getUploadCatalog() throws FileNotFoundException {
        //构建上传目标路径，找到了项目的target的classes目录
        File rootDirectoryFile = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!rootDirectoryFile.exists()) {
            return "创建根目录失败！";
        }
        //拼接子路径
        File uploadCatalog = new File(rootDirectoryFile.getAbsolutePath(), "picture\\");
        //若目标文件夹不存在，则创建
        if (!uploadCatalog.exists()) {
            uploadCatalog.mkdirs();
        }
        System.out.println("上传目录：" + uploadCatalog.getAbsolutePath());
        return uploadCatalog.getAbsolutePath();
    }



}
