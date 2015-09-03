package org.iot.server;

import java.security.Principal;
import java.util.List;

import org.iot.server.to.BeaconTo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
@EnableScheduling
@RestController
public class IoTServerApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(IoTServerApplication.class, args);
	}
	
	
	  @RequestMapping("/services/user")
	  public Principal user(Principal user) {
		  System.out.println(user);
		  
	    return user;
	  }
}
