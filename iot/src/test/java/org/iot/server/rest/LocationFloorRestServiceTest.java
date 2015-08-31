package org.iot.server.rest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.iot.server.service.LocationFloorService;
import org.iot.server.to.LocationFloorTo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LocationFloorRestServiceTest {

	private LocationFloorRestService testedObject;
	
	@Mock
	private LocationFloorService locationFloorServiceMock;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		testedObject = new LocationFloorRestService(locationFloorServiceMock);
	}
	
	@Test
	public void test() {
		final List<LocationFloorTo> floorsFromMock = new ArrayList<>();
		floorsFromMock.add(new LocationFloorTo("building", 2));
		when(locationFloorServiceMock.getAllAvailableFloors()).thenReturn(floorsFromMock);
		
		final List<LocationFloorTo> result = testedObject.getAvailableFloors();
		
		verify(locationFloorServiceMock).getAllAvailableFloors();
		assertEquals(floorsFromMock, result);
	}

}
