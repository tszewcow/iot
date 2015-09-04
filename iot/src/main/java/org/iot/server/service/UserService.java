package org.iot.server.service;

import java.util.List;

import org.iot.server.document.User;
import org.iot.server.to.UserTo;

public interface UserService {

	List<UserTo> getAllUsers();

	User getUser(String userEmail);
	
	UserTo addUser(UserTo userTo);

	void deleteUser(String id);

	UserTo updateUser(UserTo request);
}
