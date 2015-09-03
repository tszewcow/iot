package org.iot.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addViewController("/home").setViewName("home");
    	registry.addViewController("/main/welcome").setViewName("welcome");
        registry.addViewController("/main/welcome").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        
        
        registry.addViewController("/services/login").setViewName("/services/login");
        registry.addViewController("/services/login").setViewName("login");
        registry.addViewController("/login").setViewName("login");
    }
}
