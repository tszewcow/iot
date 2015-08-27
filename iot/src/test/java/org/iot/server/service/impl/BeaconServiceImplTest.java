package org.iot.server.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.iot.server.document.Beacon;
import org.iot.server.document.BeaconStatus;
import org.iot.server.helper.EqualityChecker;
import org.iot.server.helper.TestDataGenerator;
import org.iot.server.mapper.BeaconMapper;
import org.iot.server.mapper.BeaconStatusMapper;
import org.iot.server.repository.BeaconRepository;
import org.iot.server.repository.BeaconStatusRepository;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class BeaconServiceImplTest
{
	@Captor
    private ArgumentCaptor<List<BeaconStatus>> beaconStatusesListCaptor;
	@Captor
    private ArgumentCaptor<List<Beacon>> beaconsListCaptor;
	
	
	@Mock
	private BeaconStatusRepository beaconStatusRepository;
	@Spy
	private BeaconStatusMapper beaconStatusMapper;
	
	@Mock
	private BeaconRepository beaconRepository;
	@Spy
	private BeaconMapper beaconMapper;
	
	@InjectMocks
	private BeaconServiceImpl beaconServiceImpl;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testInitialization()
	{
		assertEquals(true, beaconServiceImpl != null);
	}
	
	@Test
	public void testGetAllBeaconsStatuses()
	{
		List<BeaconStatusTo> beaconStatusesList = beaconServiceImpl.getAllBeaconsStatuses();
		
		assertEquals(true, beaconStatusesList != null);
		
		verify(beaconStatusRepository).findAll();

		verify(beaconStatusMapper).mapDocuments2Tos(beaconStatusesListCaptor.capture());
		
		assertEquals(true, beaconStatusesListCaptor.getAllValues() != null);
	}
	
	@Test
	public void testRegisterStatus()
	{
		BeaconStatusTo statusTo = TestDataGenerator.getBeaconStatusTo();
		
		beaconServiceImpl.registerStatus(statusTo);
		
		ArgumentCaptor<BeaconStatusTo> argumentToCaptor = ArgumentCaptor.forClass(BeaconStatusTo.class);
		verify(beaconStatusMapper).mapTo2Document(argumentToCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(statusTo, argumentToCaptor.getValue()));
		
		ArgumentCaptor<BeaconStatus> argumentCaptor = ArgumentCaptor.forClass(BeaconStatus.class);
		verify(beaconStatusRepository).save(argumentCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(argumentCaptor.getValue(), statusTo));
	}
	
	
	
	@Test
	public void testGetAllBeacons()
	{
		List<BeaconTo> beaconsList = beaconServiceImpl.getAllBeacons();
		
		assertNotNull(beaconsList);
		
		verify(beaconRepository).findAll();

		verify(beaconMapper).mapDocuments2Tos(beaconsListCaptor.capture());
		
		assertEquals(true, beaconsListCaptor.getAllValues() != null);
	}
	
	@Test
	public void testAddBeacon()
	{
		BeaconTo beaconTo = TestDataGenerator.getBeaconTo();
		BeaconTo addedData = beaconServiceImpl.addBeacon(beaconTo);
		assertEquals(true, EqualityChecker.checkEquality(beaconTo, addedData));
		
		ArgumentCaptor<BeaconTo> beaconToCaptor = ArgumentCaptor.forClass(BeaconTo.class);
		verify(beaconMapper).mapTo2Document(beaconToCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(beaconTo, beaconToCaptor.getValue()));
		
		ArgumentCaptor<Beacon> beaconCaptor = ArgumentCaptor.forClass(Beacon.class);
		verify(beaconRepository).save(beaconCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(beaconCaptor.getValue(), beaconTo));
		
		beaconCaptor = ArgumentCaptor.forClass(Beacon.class);
		verify(beaconMapper).mapDocument2To(beaconCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(beaconCaptor.getValue(), beaconTo));
	}
	
	@Test
	public void testDeleteBeacon()
	{
		Beacon beacon = TestDataGenerator.getBeacon();
		beaconServiceImpl.deleteBeacon(beacon.getId());
		
		ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);
		verify(beaconRepository).delete(idCaptor.capture());
		assertEquals(beacon.getId(), idCaptor.getValue());
	}
	
	
	@Test
	public void testUpdateBeacon()
	{
		BeaconTo beaconTo = TestDataGenerator.getBeaconTo();
		BeaconTo addedData = beaconServiceImpl.updateBeacon(beaconTo);
		assertEquals(true, EqualityChecker.checkEquality(beaconTo, addedData));
		
		ArgumentCaptor<BeaconTo> beaconToCaptor = ArgumentCaptor.forClass(BeaconTo.class);
		verify(beaconMapper).mapTo2Document(beaconToCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(beaconTo, beaconToCaptor.getValue()));
		
		ArgumentCaptor<Beacon> beaconCaptor = ArgumentCaptor.forClass(Beacon.class);
		verify(beaconRepository).save(beaconCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(beaconCaptor.getValue(), beaconTo));
		
		beaconCaptor = ArgumentCaptor.forClass(Beacon.class);
		verify(beaconMapper).mapDocument2To(beaconCaptor.capture());
		assertEquals(true, EqualityChecker.checkEquality(beaconCaptor.getValue(), beaconTo));
	}
}