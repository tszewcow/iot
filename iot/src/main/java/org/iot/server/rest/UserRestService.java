package org.iot.server.rest;

import java.security.Principal;
import java.util.List;

import org.iot.server.service.UserService;
import org.iot.server.to.BeaconTo;
import org.iot.server.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@SpringBootApplication
@RestController
public class UserRestService {

	private final UserService userService;

	@Autowired
	public UserRestService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/services/getAllUsers", method = RequestMethod.GET)
	public List<UserTo> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(value = "/services/getUser/{username}/{password}", method = RequestMethod.GET)
	public UserTo getUser(@PathVariable("username") String username, @PathVariable("password") String password) {	
		return userService.getUser(username, password);
	}
	
	
	@RequestMapping(value = "/services/addNewUser", method = RequestMethod.POST)
	public UserTo addUser(@RequestBody UserTo request) {
		return userService.addUser(request);
	}
	
	
	@RequestMapping(value = "user", method = RequestMethod.GET)
	  public Principal user(@RequestBody Principal string) {
		System.out.println("user");
		
	    return string;
	  }
}
