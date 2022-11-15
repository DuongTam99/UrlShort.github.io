package com.example.demoUrlShortGroovy.config


import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Configuration
class FilterConfig {
    @Bean
    FilterRegistrationBean<ValidateFilter> doFil() {
        FilterRegistrationBean<ValidateFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ValidateFilter())
        registrationBean.addUrlPatterns("/generate")

        return registrationBean
    }
}
