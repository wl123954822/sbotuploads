package com.wl.fileupload2.config;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;
import javax.validation.Valid;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {


    /**
     * 在配置文件中配置下文件的路径
     */
    @Value("${img.location}")
    private String location;

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();

        //文件最大KB,MB
        factory.setMaxFileSize("2MB");
        //设置上传总数据大小
        factory.setMaxRequestSize("10MB");
        return factory.createMultipartConfig();
    }
}
