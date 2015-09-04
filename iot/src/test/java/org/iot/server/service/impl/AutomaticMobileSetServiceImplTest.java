package org.iot.server.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		AutomaticMobileSetTo dataToSave = TestDataGenerator.getAutomaticMobileSetTo(1);
		
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
		AutomaticMobileSet ams = TestDataGenerator.getAutomaticMobileSet(1);
		
		automaticMobileSetServiceImpl.deleteAutomaticMobileSet(ams.getId());
		
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
		
		verify(automaticMobileSetRepository).delete(argumentCaptor.capture());
		
		assertEquals(ams.getId(), argumentCaptor.getValue());
	}
	
	@Test
	public void testUpdateAutomaticMobileSet()
	{
		AutomaticMobileSetTo dataToSave = TestDataGenerator.getAutomaticMobileSetTo(1);
		
		AutomaticMobileSetTo savedData = automaticMobileSetServiceImpl.updateAutomaticMobileSet(dataToSave);
		
		assertEquals(true, EqualityChecker.checkEquality(dataToSave, savedData));
		
		ArgumentCaptor<AutomaticMobileSet> argumentCaptor = ArgumentCaptor.forClass(AutomaticMobileSet.class);
		verify(automaticMobileSetRepository).save(argumentCaptor.capture());
		
		assertEquals(true, EqualityChecker.checkEquality(argumentCaptor.getValue(), dataToSave));
	}
	
	@Test
	public void shouldReturnFilteredAMSes() {
		final List<AutomaticMobileSet> allAMSes = Stream.of(
				createAMS("id1", "MTII", 8),
				createAMS("id2", "MTII", 7),
				createAMS("id3", "MTIV", 7),
				createAMS("id4", "MTIV", 8),
				createAMS("id5", "MTIV", 9),
				createAMS("id6", "MTIV", 8),
				createAMS("id7", "MTII", 8)
				).collect(Collectors.toList());
		when(automaticMobileSetRepository.findAll()).thenReturn(allAMSes);
		
		final List<AutomaticMobileSetTo> result = automaticMobileSetServiceImpl.getAutomaticMobileSets("MTIV", 8);
		
		verify(automaticMobileSetRepository).findAll();
		assertEquals(2, result.size());
		assertEquals("id4", result.get(0).getId());
		assertEquals("id6", result.get(1).getId());
	}
	
	private AutomaticMobileSet createAMS(final String id, final String building, final int floor) {
		final AutomaticMobileSet ams = new AutomaticMobileSet();
		ams.setId(id);
		ams.setBuilding(building);
		ams.setFloor(floor);
		return ams;
	}
	
	@Test
	public void shouldReturnEmptyListOfFilteredAMSes() {
		final List<AutomaticMobileSet> allAMSes = Stream.of(
				createAMS("id1", "MTII", 6),
				createAMS("id2", "MTII", 12),
				createAMS("id3", "MTIV", 7),
				createAMS("id4", "MTIV", 8),
				createAMS("id5", "MTIV", 9),
				createAMS("id6", "MTIV", 8),
				createAMS("id7", "MTII", 6)
				).collect(Collectors.toList());
		when(automaticMobileSetRepository.findAll()).thenReturn(allAMSes);
		
		final List<AutomaticMobileSetTo> result = automaticMobileSetServiceImpl.getAutomaticMobileSets("MTIV", 12);
		
		verify(automaticMobileSetRepository).findAll();
		assertTrue(result.isEmpty());
	}
	
}
