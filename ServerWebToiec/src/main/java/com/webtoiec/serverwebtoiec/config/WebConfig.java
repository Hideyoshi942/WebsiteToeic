package com.webtoiec.serverwebtoiec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/img/**")
        .addResourceLocations("classpath:/static/img/");

    registry.addResourceHandler("/audio/**")
        .addResourceLocations("classpath:/static/audio/");

    registry.addResourceHandler("/js/**")
        .addResourceLocations("classpath:/static/js/");

    registry.addResourceHandler("/excel/**")
        .addResourceLocations("classpath:/static/excel/");
  }
}
