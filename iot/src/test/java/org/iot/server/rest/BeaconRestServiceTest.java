package org.iot.server.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.iot.server.helper.EqualityChecker;
import org.iot.server.helper.TestDataGenerator;
import org.iot.server.service.BeaconService;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BeaconRestServiceTest
{
	@Mock
	private BeaconService beaconService;
	@Mock
	private RequestToBeaconStatusToConverter requestToBeaconStatusToConverter;

	@InjectMocks
	private BeaconRestService beaconRestService;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testInitialization()
	{
		assertNotNull(beaconRestService);
	}

	@Test
	public void testGetAllBeacons()
	{
		List<BeaconTo> beaconToList = beaconRestService.getAllBeacons();
		
		assertNotNull(beaconToList);
		
		verify(beaconService).getAllBeacons();
	}

	@Test
	public void testReportStatus() 
	{
		String requestString = "\"Major=65535&UUDI=7e1c699dd541dd5ee61ea67d9479c28c&MAC=20%3Afa%3Abb%3A01%3A77%3Ae0&RSSI=-50&Minor=189&MeasuredStrenght=-61\"";
		
		beaconRestService.reportStatus(requestString);
		
		ArgumentCaptor<String> requestCaptor = ArgumentCaptor.forClass(String.class);
		verify(requestToBeaconStatusToConverter).convert(requestCaptor.capture());
		
		assertEquals(requestString, requestCaptor.getValue());
	}

	@Test
	public void testGetAllBeaconsStatuses()
	{
		List<BeaconStatusTo> beaconStatusToList = beaconRestService.getAllBeaconsStatuses();
		assertNotNull(beaconStatusToList);
		verify(beaconService).getAllBeaconsStatuses();
	}

	@Test
	public void testAddBeacon()
	{
		BeaconTo beaconTo = TestDataGenerator.getBeaconTo(1);
		beaconRestService.addBeacon(beaconTo);
		
		ArgumentCaptor<BeaconTo> requestCaptor = ArgumentCaptor.forClass(BeaconTo.class);
		verify(beaconService).addBeacon(requestCaptor.capture());
		
		assertEquals(true, EqualityChecker.checkEquality(beaconTo, requestCaptor.getValue()));
	}

	@Test
	public void testDeleteBeacon()
	{
		String id = TestDataGenerator.randomString();
		
		beaconRestService.deleteBeacon(id);
		
		ArgumentCaptor<String> requestCaptor = ArgumentCaptor.forClass(String.class);
		verify(beaconService).deleteBeacon(requestCaptor.capture());
		
		assertEquals(id, requestCaptor.getValue());
	}

	@Test
	public void testUpdateBeacon()
	{
		BeaconTo beaconTo = TestDataGenerator.getBeaconTo(1);
		beaconRestService.updateBeacon(beaconTo);
		
		ArgumentCaptor<BeaconTo> requestCaptor = ArgumentCaptor.forClass(BeaconTo.class);
		verify(beaconService).updateBeacon(requestCaptor.capture());
		
		assertEquals(true, EqualityChecker.checkEquality(beaconTo, requestCaptor.getValue()));
	}
}
