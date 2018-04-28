package com.wl.fileupload2.controller;

import com.wl.fileupload2.Enums.ResultEnum;
import com.wl.fileupload2.Vo.MarkDVo;
import com.wl.fileupload2.exception.BusinessException;
import com.wl.fileupload2.utils.ImageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
public class UploadController {

    @Value("${img.location}")
    private String location;

       private final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @PutMapping("/article/img/upload")
    public MarkDVo uploadImg(@RequestParam("imgFile") MultipartFile multipartFile) {
        if (multipartFile.isEmpty() || StringUtils.isBlank(multipartFile.getOriginalFilename())) {
            throw new BusinessException(ResultEnum.IMG_NOT_EMPTY);
        }

        String contentType = multipartFile.getContentType();
        if (!contentType.contains("")) {
            throw new BusinessException(ResultEnum.IMG_FORMAT_ERROR);
        }

        String root_fileName = multipartFile.getOriginalFilename();
        logger.info("上传图片:name={},type={}", root_fileName, contentType);

        //处理图片
        //todo

        //获取路径
        String return_path = ImageUtil.getFilePath();
        String filePath = location + return_path;
        logger.info("图片保存路径={}", filePath);
        String file_name = null;

        try {
            file_name = ImageUtil.saveImg(multipartFile, filePath);
            MarkDVo markDVo = new MarkDVo();
            markDVo.setSuccess(0);
            if (StringUtils.isNotBlank(file_name)) {
                markDVo.setSuccess(1);
                markDVo.setMessage("上传成功");
                markDVo.setUrl(return_path + File.separator + file_name);
            }
            logger.info("返回值：{}", markDVo);
            return markDVo;
        } catch (IOException e) {
            throw new BusinessException(ResultEnum.SAVE_IMG_ERROE);
        }
    }
}
