package org.iot.server.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Ignore;
import org.iot.server.document.User;
import org.iot.server.helper.EqualityChecker;
import org.iot.server.helper.TestDataGenerator;
import org.iot.server.mapper.UserMapper;
import org.iot.server.repository.UserRepository;
import org.iot.server.to.UserTo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class UserServiceImplTest
{
	@Captor
    private ArgumentCaptor<List<User>> usersListCaptor;
	
	
	@Mock
	private UserRepository userRepository;
	@Spy
	private UserMapper userMapper;
	
	
	@InjectMocks
	private UserServiceImpl testedObject;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testInitialization()
	{
		assertEquals(true, testedObject != null);
	}


	@Test
	public void testGetAllUsers()
	{
		int userDataListSize = 10;
		
		List<User> resultUserList = new ArrayList<User>();
		
		for(int x=0;x<userDataListSize;x++)
			resultUserList.add(TestDataGenerator.getUser(x));
		
		when(userRepository.findAll()).thenReturn(resultUserList);
		
		List<UserTo> usersList = testedObject.getAllUsers();
		
		verify(userRepository).findAll();

		verify(userMapper).mapDocuments2Tos(usersListCaptor.capture());
		
		assertNotNull(usersList);
		assertEquals(userDataListSize, usersList.size());
		assertEquals(userDataListSize, usersListCaptor.getValue().size());
	}
	
	

    // TODO MS: repair this test
    @Ignore
	@Test
	public void testAdduser()
	{
		UserTo userTo = TestDataGenerator.getUserTo(1);
		UserTo addedData = testedObject.addUser(userTo);
		
		//createdOn is added in the addUser() function- usetTo does not have the creation date
		userTo.setCreatedOn(addedData.getCreatedOn());
		
		assertEquals(true, EqualityChecker.checkEquality(userTo, addedData));
		
		ArgumentCaptor<UserTo> userToCaptor = ArgumentCaptor.forClass(UserTo.class);
		verify(userMapper).mapTo2Document(userToCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(userTo, userToCaptor.getValue()));
		
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		verify(userRepository).save(userCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(userCaptor.getValue(), userTo));
		
		userCaptor = ArgumentCaptor.forClass(User.class);
		verify(userMapper).mapDocument2To(userCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(userCaptor.getValue(), userTo));
	}
	
	@Test
	public void testDeleteUser()
	{
		User user = TestDataGenerator.getUser(1);
		testedObject.deleteUser(user.getId());
		
		ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);
		verify(userRepository).delete(idCaptor.capture());
		assertEquals(user.getId(), idCaptor.getValue());
	}

    // TODO MS: repair this test
    @Ignore
	@Test
	public void testUpdateUser()
	{
		UserTo userTo = TestDataGenerator.getUserTo(1);
		UserTo addedData = testedObject.updateUser(userTo);
		assertEquals(true, EqualityChecker.checkEquality(userTo, addedData));
		
		ArgumentCaptor<UserTo> userToCaptor = ArgumentCaptor.forClass(UserTo.class);
		verify(userMapper).mapTo2Document(userToCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(userTo, userToCaptor.getValue()));
		
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		verify(userRepository).save(userCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(userCaptor.getValue(), userTo));
		
		userCaptor = ArgumentCaptor.forClass(User.class);
		verify(userMapper).mapDocument2To(userCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(userCaptor.getValue(), userTo));
	}
	
	@Test
	public void shouldReturnAllUsers()
	{
		int dataListSize = 10;
		
		List<User> userList = new ArrayList<User>();
		
		for(int x=0;x<dataListSize;x++)
			userList.add(TestDataGenerator.getUser(x));
				
		when(userRepository.findAll()).thenReturn(userList);
				
		List<UserTo> result = testedObject.getAllUsers();
		
		verify(userRepository).findAll();
		
		assertEquals(dataListSize, result.size());
		
		//we can't get passwords from repository
		userList.forEach(u -> u.setPassword(null));
		
		for(int x=0;x<dataListSize;x++)
			assertEquals(true, EqualityChecker.checkEquality(userList.get(x), result.get(x)));
	}

	@Test
	public void shouldReturnEmptyListWhenNoUsers() {
		when(userRepository.findAll()).thenReturn(Collections.emptyList());
		
		final List<UserTo> result = testedObject.getAllUsers();
		
		verify(userRepository).findAll();
		assertTrue(result.isEmpty());
	}

	@Test
	public void shouldReturnOneUser()
	{
		int dataListSize = 10;
		
		List<User> userList = new ArrayList<User>();
		
		for(int x=0;x<dataListSize;x++)
			userList.add(TestDataGenerator.getUser(x));
		
		when(userRepository.findAll()).thenReturn(userList);
		
		User foundUser = testedObject.getUser(userList.get(0).getEmail());
		
		verify(userRepository).findAll();
		assertEquals(foundUser.getEmail(), userList.get(0).getEmail());
	}
	
	@Test
	public void shouldNotReturnUser()
	{
		int dataListSize = 10;
		
		List<User> userList = new ArrayList<User>();
		
		for(int x=0;x<dataListSize;x++)
			userList.add(TestDataGenerator.getUser(x));
		
		when(userRepository.findAll()).thenReturn(userList);
		
		User foundUser = testedObject.getUser(userList.get(1).getEmail());
		
		verify(userRepository).findAll();
		assertNotEquals(foundUser.getEmail(), userList.get(0).getEmail());
	}
}