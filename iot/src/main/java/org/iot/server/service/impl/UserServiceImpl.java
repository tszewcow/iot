package org.iot.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.iot.server.document.Beacon;
import org.iot.server.document.User;
import org.iot.server.mapper.UserMapper;
import org.iot.server.repository.UserRepository;
import org.iot.server.service.UserService;
import org.iot.server.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public List<UserTo> getAllUsers() {
		List<User> users = userRepository.findAll();
		return userMapper.mapDocuments2Tos(users);
	}

	
	//TODO zorbic unique login na bazie!!!!
	@Override
	public User getUser(String userEmail) {
		Predicate<User> userFilter = user -> user.getEmail().equals(userEmail);
		
		List<User> users = userRepository.findAll().stream().filter(userFilter).collect(Collectors.toList());
		
		if(users != null && users.size() == 1)
			return users.get(0);
		
		return new User();
	}

	@Override
	public UserTo addUser(UserTo userTo)
	{
		User user = userMapper.mapTo2Document(userTo);

		user.setCreatedOn(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		userRepository.save(user);

		return userMapper.mapDocument2To(user);
	}

	@Override
	public void deleteUser(String id) {
		userRepository.delete(id);
	}

	@Override
	public UserTo updateUser(UserTo userTo) {
		User user = userMapper.mapTo2Document(userTo);
		userRepository.save(user);
		return userMapper.mapDocument2To(user);
	}
}