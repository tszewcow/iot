package org.iot.server.service;

import java.util.List;

import org.iot.server.to.BeaconTo;
import org.iot.server.to.UserTo;

public interface UserService {

	List<UserTo> getAllUsers();

	UserTo getUser(String userName, String password);
	
	UserTo addUser(UserTo userTo);
}
