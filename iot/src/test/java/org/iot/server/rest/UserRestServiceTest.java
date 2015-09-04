package org.iot.server.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.iot.server.document.User;
import org.iot.server.helper.EqualityChecker;
import org.iot.server.helper.TestDataGenerator;
import org.iot.server.service.UserService;
import org.iot.server.to.UserTo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserRestServiceTest
{
	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserRestService userRestService;
	
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testInitialization()
	{
		assertNotNull(userRestService);
	}
	

	
	@Test
	public void testGetAllUsers()
	{
		List<UserTo> userList = userRestService.getAllUsers();
		assertNotNull(userList);
		
		verify(userService).getAllUsers();
	}
	
	@Test
	public void testAddUser()
	{
		UserTo userTo = TestDataGenerator.getUserTo(1);
		userRestService.addUser(userTo);
		
		ArgumentCaptor<UserTo> argumentCaptor = ArgumentCaptor.forClass(UserTo.class);
		verify(userService).addUser(argumentCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(userTo, argumentCaptor.getValue()));
	}
	
	@Test
	public void testDeleteUser()
	{
		User user = TestDataGenerator.getUser(1);
		userRestService.deleteUser(user.getId());
		
		ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);
		verify(userService).deleteUser(idCaptor.capture());
		assertEquals(user.getId(), idCaptor.getValue());
	}
	
	@Test
	public void testUpdateUser()
	{
		UserTo userTo = TestDataGenerator.getUserTo(1);
		userRestService.updateUser(userTo);
		
		ArgumentCaptor<UserTo> argumentCaptor = ArgumentCaptor.forClass(UserTo.class);
		verify(userService).updateUser(argumentCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(userTo, argumentCaptor.getValue()));
	}
}
