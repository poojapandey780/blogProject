package com.pooja.blogProject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    this comes from application properties
    @Value("${app.upload.dir}")
    private String uploadDir;


    @Override public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/img/**")
            .addResourceLocations("file:" + uploadDir + "/");
    }
}
