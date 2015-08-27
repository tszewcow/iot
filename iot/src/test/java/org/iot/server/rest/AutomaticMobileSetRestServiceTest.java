package org.iot.server.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.iot.server.document.AutomaticMobileSet;
import org.iot.server.helper.EqualityChecker;
import org.iot.server.helper.TestDataGenerator;
import org.iot.server.service.AutomaticMobileSetService;
import org.iot.server.to.AutomaticMobileSetTo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AutomaticMobileSetRestServiceTest
{
	@Mock
	private AutomaticMobileSetService automaticMobileSetService;
	
	@InjectMocks
	private AutomaticMobileSetRestService automaticMobileSetRestService;
	
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testInitialization()
	{
		assertNotNull(automaticMobileSetRestService);
	}
	

	
	@Test
	public void testGetAllAutomaticMobileSets()
	{
		List<AutomaticMobileSetTo> amsList = automaticMobileSetRestService.getAllAutomaticMobileSets();
		assertNotNull(amsList);
		
		verify(automaticMobileSetService).getAllAutomaticMobileSets();
	}
	
	@Test
	public void testAddAutomaticMobileSet()
	{
		AutomaticMobileSetTo amsTo = TestDataGenerator.getAutomaticMobileSetTo();
		automaticMobileSetRestService.addAutomaticMobileSet(amsTo);
		
		ArgumentCaptor<AutomaticMobileSetTo> argumentCaptor = ArgumentCaptor.forClass(AutomaticMobileSetTo.class);
		verify(automaticMobileSetService).addAutomaticMobileSet(argumentCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(amsTo, argumentCaptor.getValue()));
	}
	
	@Test
	public void testDeleteAutomaticMobileSet()
	{
		AutomaticMobileSet ams = TestDataGenerator.getAutomaticMobileSet();
		automaticMobileSetRestService.deleteAutomaticMobileSet(ams.getId());
		
		ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);
		verify(automaticMobileSetService).deleteAutomaticMobileSet(idCaptor.capture());
		assertEquals(ams.getId(), idCaptor.getValue());
	}
	
	@Test
	public void testUpdateAutomaticMobileSet()
	{
		AutomaticMobileSetTo amsTo = TestDataGenerator.getAutomaticMobileSetTo();
		automaticMobileSetRestService.updateAutomaticMobileSet(amsTo);
		
		ArgumentCaptor<AutomaticMobileSetTo> argumentCaptor = ArgumentCaptor.forClass(AutomaticMobileSetTo.class);
		verify(automaticMobileSetService).updateAutomaticMobileSet(argumentCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(amsTo, argumentCaptor.getValue()));
	}
}
