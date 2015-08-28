package org.iot.server.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.iot.server.document.Beacon;
import org.iot.server.mapper.BeaconMapper;
import org.iot.server.mapper.BeaconStatusMapper;
import org.iot.server.repository.BeaconRepository;
import org.iot.server.repository.BeaconStatusRepository;
import org.iot.server.to.BeaconTo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BeaconServiceImplTest {

	@Mock
	private BeaconStatusRepository beaconStatusRepository;
	
	@Mock
	private BeaconRepository beaconRepository;
	
	@InjectMocks
	private BeaconServiceImpl testedObject;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		testedObject = new BeaconServiceImpl(beaconStatusRepository, new BeaconStatusMapper(), beaconRepository, new BeaconMapper());
	}
	
	@Test
	public void shouldReturnAllBeacons() {
		final String id1 = "first";
		final String id2 = "sECond";
		final String id3 = "THird";
		final List<Beacon> allBeacons = createSampleBeacons(id1, id2, id3);
		when(beaconRepository.findAll()).thenReturn(allBeacons);
		
		final List<BeaconTo> result = testedObject.getAllBeacons();
		
		verify(beaconRepository).findAll();
		assertEquals(3, result.size());
		assertEquals(id1, result.get(0).getId());
		assertEquals(id2, result.get(1).getId());
		assertEquals(id3, result.get(2).getId());
	}

	private List<Beacon> createSampleBeacons(final String... ids) {
		Function<String, Beacon> createBeacon = id -> {Beacon beacon = new Beacon();
			beacon.setId(id);
			return beacon;
		}; 
		List<Beacon> beacons = Stream.of(ids).map(createBeacon).collect(Collectors.toList());;
		return beacons;
	}
	
	@Test
	public void shouldReturnEmptyListWhenNoBeacons() {
		when(beaconRepository.findAll()).thenReturn(Collections.emptyList());
		
		final List<BeaconTo> result = testedObject.getAllBeacons();
		
		verify(beaconRepository).findAll();
		assertTrue(result.isEmpty());
	}

	@Test
	public void shouldReturnFilteredBeacons() {
		final List<Beacon> allBeacons = Stream.of(
				createBeacon("id1", "MTII", 6),
				createBeacon("id2", "MTII", 7),
				createBeacon("id3", "MTIV", 7),
				createBeacon("id4", "MTIV", 8),
				createBeacon("id5", "MTIV", 9),
				createBeacon("id6", "MTIV", 8),
				createBeacon("id7", "MTII", 6)
				).collect(Collectors.toList());
		when(beaconRepository.findAll()).thenReturn(allBeacons);
		
		final List<BeaconTo> result = testedObject.getBeacons("MTIV", 8);
		
		verify(beaconRepository).findAll();
		assertEquals(2, result.size());
		assertEquals("id4", result.get(0).getId());
		assertEquals("id6", result.get(1).getId());
	}
	
	private Beacon createBeacon(final String id, final String building, final int floor) {
		Beacon beacon = new Beacon();
		beacon.setId(id);
		beacon.setBuilding(building);
		beacon.setFloor(floor);
		return beacon;
	}
	
	@Test
	public void shouldReturnEmptyFilteredBeaconsWhenNoneMatch() {
		final List<Beacon> allBeacons = Stream.of(
				createBeacon("id1", "MTII", 6),
				createBeacon("id2", "MTII", 7),
				createBeacon("id3", "MTIV", 7),
				createBeacon("id4", "MTIV", 8),
				createBeacon("id5", "MTIV", 9),
				createBeacon("id6", "MTIV", 8),
				createBeacon("id7", "MTII", 6)
				).collect(Collectors.toList());
		when(beaconRepository.findAll()).thenReturn(allBeacons);
		
		final List<BeaconTo> result = testedObject.getBeacons("MTIV", 12);
		
		verify(beaconRepository).findAll();
		assertTrue(result.isEmpty());
	}
	
}
