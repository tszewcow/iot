package org.iot.server.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.iot.server.document.User;
import org.iot.server.to.UserTo;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	public UserTo mapDocument2To(User user)
	{
		UserTo userTo = new UserTo();
		
		userTo.setId(user.getId());
		userTo.setFirstName(user.getFirstName());
		userTo.setLastName(user.getLastName());
		userTo.setEmail(user.getEmail());
		userTo.setPassword(user.getPassword());
		userTo.setSalt(user.getSalt());
		userTo.setCreatedOn(user.getCreatedOn());
		userTo.setLastLogin(user.getLastLogin());
		userTo.setUserRole(user.getUserRole());
		userTo.setActive(user.isActive());

		return userTo;
	}

	public List<UserTo> mapDocuments2Tos(List<User> users) {

		return users.stream().map(this::mapDocument2To).collect(Collectors.toList());
	}

	public User mapTo2Document(UserTo userTo) {

		User user = new User();
		
		user.setId(userTo.getId());
		user.setFirstName(userTo.getFirstName());
		user.setLastName(userTo.getLastName());
		user.setEmail(userTo.getEmail());
		user.setPassword(userTo.getPassword());
		user.setSalt(userTo.getSalt());
		user.setCreatedOn(userTo.getCreatedOn());
		user.setLastLogin(userTo.getLastLogin());
		user.setUserRole(userTo.getUserRole());
		user.setActive(userTo.isActive());

		return user;
	}
}
