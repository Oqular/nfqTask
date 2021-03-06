package com.example.nfqtask.Config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class MvcConfig: WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry){
        registry.addViewController("/login").setViewName("login")
//        registry.addViewController("/home").setViewName("home")
    }
}