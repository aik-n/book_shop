package com.book.config;

import com.book.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new UserFilter());
//        filterRegistrationBean.addUrlPatterns("/cart/*","/order/*","/user/userInfo","/userAddress/*","/admin");
        filterRegistrationBean.addUrlPatterns("/admin","/addBook","/selectBook","/selectOrder","/selectUser");
        return filterRegistrationBean;
    }
}
