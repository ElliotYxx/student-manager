package com.sheva.studentmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Sheva
 * @data 2019/11/1  下午8:28
 * @Version 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 添加自定义的参数解析器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver());
    }

    @Bean
    UserArgumentResolver userArgumentResolver(){
        return new UserArgumentResolver();
    }
}
