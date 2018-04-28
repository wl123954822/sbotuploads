package com.wl.sbupload.utile;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;

public class FileUtil {
    public static void uploadFile(byte[] file,  String fileName) throws Exception {
        //获取跟目录
        File path = new File(ResourceUtils.getURL("classpath:").getPath());

        String pathUrl = path.getAbsolutePath();
        String path2 = URLDecoder.decode(pathUrl, "utf-8");
        File targetFile = new File(path2,"static/images/upload/");
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(targetFile.getAbsolutePath()+"/"+fileName);
        System.out.println(targetFile.getAbsolutePath()+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
