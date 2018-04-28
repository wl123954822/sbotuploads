package com.wl.sbupload.controller;
import com.wl.sbupload.utile.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

@Controller
public class FileUploadController {
   //跳转到上传文件的页面
    @RequestMapping(value="/gouploadimg", method = RequestMethod.GET)
    public String goUploadImg() {
        //跳转到 templates 目录下的 uploadimg.html
        return "uploadimg";
    }

    //处理文件上传
    @RequestMapping(value="/testuploadimg", method = RequestMethod.POST)
    public @ResponseBody
    String uploadImg(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request) throws FileNotFoundException {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        try {
            FileUtil.uploadFile(file.getBytes(), fileName);


        } catch (Exception e) {
            // TODO: handle exception
        }

        return "uploadimg success";
    }
}
