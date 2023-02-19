package com.itheima.reggie.config;

import com.itheima.reggie.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @className: WebMvcConfig
 * @description: TODO 类描述
 * @author: Figure
 * @date: 2023/02/06 16:36
 **/
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /*
     * @Author Figure
     * @Description 设置静态资源映射
     * @Date 16:37 2023/2/6
     * @Param [registry]
     * @return void
     **/
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始静态资源映射");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

    /**
     * @Author Figure
     * @Description 拓展mvc框架的消息转换器
     * @Date 23:26 2023/2/7
     * @Param [converters]
     * @return void
     **/
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("拓展消息转换器");
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用将Jackson将Java对象转为Json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将消息转换器对象追加到mvc框架的转换器集合中
        converters.add(0, messageConverter);
    }
}
