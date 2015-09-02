package org.iot.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@SpringBootApplication
@EnableScheduling
@RestController
public class IoTServerApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(IoTServerApplication.class, args);
	}
	
	
	
	@RequestMapping("services//login")
    public String login() {
		
		System.out.println("login TTTTTTTTT");
		return "resr";
	}
	
	
	
	
	
	@RequestMapping("services/user")
    public Principal user(Principal user) {
		
		System.out.println("jest");
		
		System.out.println(user != null ? user.getClass() : "null");
		
		System.out.println();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		System.out.println(principal.getClass());
		
		System.out.println(principal);
		
        return user;
    }
	
//	@RequestMapping("/user")
//    public Principal user(Principal user) {
//        return user;
//    }

    @RequestMapping("/resource")
    public Map<String, Object> home() {
        Map<String, Object> model = new HashMap<>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main/login").setViewName("/main/login");
        
        System.out.println("addViewControllers");
    }
}
