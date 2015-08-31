package org.iot.server.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.iot.server.to.LocationFloorTo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MockedLocationFloorServiceImplTest {

	private static final String MT2_BUILDING_NAME = "MT2";
	
	private MockedLocationFloorServiceImpl testedObject;
	
	@Before
	public void setUp() {
		testedObject = new MockedLocationFloorServiceImpl();
	}
	
	@Test
	public void shouldReturnMockedFloors() {
		final List<LocationFloorTo> expectedFloors = Stream.of(
				new LocationFloorTo(MT2_BUILDING_NAME, 6),
				new LocationFloorTo(MT2_BUILDING_NAME, 7),
				new LocationFloorTo(MT2_BUILDING_NAME, 8),
				new LocationFloorTo(MT2_BUILDING_NAME, 9),
				new LocationFloorTo(MT2_BUILDING_NAME, 10),
				new LocationFloorTo(MT2_BUILDING_NAME, 11)
				).collect(Collectors.toList());
		
		final List<LocationFloorTo> actualFloors = testedObject.getAllAvailableFloors();
		
		Assert.assertEquals(expectedFloors, actualFloors);
	}

}
