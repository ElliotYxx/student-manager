package com.sheva.studentmanager.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @author sheva
 */
public class MybatisPlusConfig {

    /**
     * 分页
     */

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
