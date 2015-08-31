package org.iot.server.service.impl;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.iot.server.document.Beacon;
import org.iot.server.document.BeaconStatus;
import org.iot.server.document.User;
import org.iot.server.mapper.BeaconMapper;
import org.iot.server.mapper.BeaconStatusMapper;
import org.iot.server.mapper.UserMapper;
import org.iot.server.repository.BeaconRepository;
import org.iot.server.repository.BeaconStatusRepository;
import org.iot.server.repository.UserRepository;
import org.iot.server.service.BeaconService;
import org.iot.server.service.UserService;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;
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

	//TODO zrobic zapytanie a nie pobieranie wszystkiego z bazy
	@Override
	public UserTo getUser(String userName, String password) {
		Predicate<User> userFilter = user -> user.getUserName().equals(userName) && user.getPassword().equals(password);
		
//		List<User> users = userRepository.findAll().stream().filter(userFilter).collect(Collectors.toList());
//		
//		if(users != null && users.size() == 1)
//			return userMapper.mapDocument2To(users.get(0));
		
		//TODO test
		UserTo test = new UserTo();
		test.setFirstName("qwertyuiop");
		
		return test;//new UserTo();
	}
}