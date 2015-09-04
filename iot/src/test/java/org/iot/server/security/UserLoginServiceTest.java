package org.iot.server.security;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.iot.server.document.User;
import org.iot.server.helper.TestDataGenerator;
import org.iot.server.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserLoginServiceTest
{
	@Captor
    private ArgumentCaptor<List<User>> usersListCaptor;
	
	
	@Mock
	private UserService userDBService;
	
	@InjectMocks
	private UserLoginService testedObject;
	
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
	public void userLoginAndPassword_OK()
	{
		User user = TestDataGenerator.getUser(1);
		
		when(userDBService.getUser(user.getEmail())).thenReturn(user);
		
		UserDetails userDetails = testedObject.loadUserByUsername(user.getEmail());
		
		verify(userDBService).getUser(user.getEmail());
		
		assertNotNull(userDetails);
		
		assertEquals(user.getEmail(), userDetails.getUsername());
		assertEquals(user.getPassword(), userDetails.getPassword());
		assertEquals(user.getUserRole(), userDetails.getAuthorities().iterator().next().getAuthority());
	}

	@Test(expected=UsernameNotFoundException.class)
	public void userLoginAndPassword_ERROR()
	{
		String email = "error";
		
		when(userDBService.getUser(email)).thenReturn(new User());
		
		testedObject.loadUserByUsername(email);		
	}
}