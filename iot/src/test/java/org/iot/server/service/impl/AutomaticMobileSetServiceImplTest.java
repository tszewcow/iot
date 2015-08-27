package org.iot.server.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.iot.server.document.AutomaticMobileSet;
import org.iot.server.helper.EqualityChecker;
import org.iot.server.helper.TestDataGenerator;
import org.iot.server.mapper.AutomaticMobileSetMapper;
import org.iot.server.repository.AutomaticMobileSetRepository;
import org.iot.server.to.AutomaticMobileSetTo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class AutomaticMobileSetServiceImplTest
{
	@Captor
    private ArgumentCaptor<List<AutomaticMobileSet>> amsListCaptor;
	
	@Spy
	private AutomaticMobileSetMapper automaticMobileSetMapper = new AutomaticMobileSetMapper();
	@Mock 
	private AutomaticMobileSetRepository automaticMobileSetRepository;
	
	@InjectMocks
	private AutomaticMobileSetServiceImpl automaticMobileSetServiceImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testInitialization()
	{
		assertNotNull(automaticMobileSetServiceImpl);
	}
	
	@Test
	public void testAddAutomaticMobileSet()
	{
		AutomaticMobileSetTo dataToSave = TestDataGenerator.getAutomaticMobileSetTo();
		
		AutomaticMobileSetTo savedData = automaticMobileSetServiceImpl.addAutomaticMobileSet(dataToSave);
		
		assertEquals(true, EqualityChecker.checkEquality(dataToSave, savedData));
		
		ArgumentCaptor<AutomaticMobileSet> argumentCaptor = ArgumentCaptor.forClass(AutomaticMobileSet.class);
		verify(automaticMobileSetRepository).save(argumentCaptor.capture());
		
		assertEquals(true, EqualityChecker.checkEquality(argumentCaptor.getValue(), dataToSave));
	}

	
	@Test
	public void testGetAllAutomaticMobileSets()
	{
		List<AutomaticMobileSetTo> amsList = automaticMobileSetServiceImpl.getAllAutomaticMobileSets();
		
		assertEquals(true, amsList != null);
		
		verify(automaticMobileSetRepository).findAll();

		verify(automaticMobileSetMapper).mapDocuments2Tos(amsListCaptor.capture());
		
		assertNotNull(amsListCaptor.getAllValues());
	}

	@Test
	public void testDeleteAutomaticMobileSet()
	{
		AutomaticMobileSet ams = TestDataGenerator.getAutomaticMobileSet();
		
		automaticMobileSetServiceImpl.deleteAutomaticMobileSet(ams.getId());
		
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
		
		verify(automaticMobileSetRepository).delete(argumentCaptor.capture());
		
		assertEquals(ams.getId(), argumentCaptor.getValue());
	}
	
	@Test
	public void testUpdateAutomaticMobileSet()
	{
		AutomaticMobileSetTo dataToSave = TestDataGenerator.getAutomaticMobileSetTo();
		
		AutomaticMobileSetTo savedData = automaticMobileSetServiceImpl.updateAutomaticMobileSet(dataToSave);
		
		assertEquals(true, EqualityChecker.checkEquality(dataToSave, savedData));
		
		ArgumentCaptor<AutomaticMobileSet> argumentCaptor = ArgumentCaptor.forClass(AutomaticMobileSet.class);
		verify(automaticMobileSetRepository).save(argumentCaptor.capture());
		
		assertEquals(true, EqualityChecker.checkEquality(argumentCaptor.getValue(), dataToSave));
	}
	
}
