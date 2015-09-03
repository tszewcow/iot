package org.iot.server.rest;

import java.util.List;

import org.iot.server.service.UserService;
import org.iot.server.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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

	@RequestMapping(value = "/services/addNewUser", method = RequestMethod.POST)
	public UserTo addUser(@RequestBody UserTo request) {
		return userService.addUser(request);
	}
	
	@RequestMapping(value = "/services/deleteUser/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("id") String id) {
		userService.deleteUser(id);
	}
	
	@RequestMapping(value = "/services/updateUser", method = RequestMethod.PUT)
	public UserTo updateUser(@RequestBody UserTo request) {
		return userService.updateUser(request);
	}
}
