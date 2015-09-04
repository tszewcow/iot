package org.iot.server.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class AdditionalRestServiceTest
{
	@InjectMocks
	private AdditionalRestService additionalRestService;
	
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testInitialization()
	{
		assertNotNull(additionalRestService);
	}
	

	
	@Test
	public void userIsLogged()
	{
		Principal p = new Principal() {
			
			@Override
			public String getName() {
				return "test";
			}
		};
		
		Principal result = additionalRestService.checkIfUserIsAuthorized(p);
		
		assertEquals(p, result);
	}
	
	@Test
	public void userIsNotLogged()
	{
		Principal principal = null;
		
		Principal result = additionalRestService.checkIfUserIsAuthorized(principal);
		
		assertEquals(principal, result);
	}
}
