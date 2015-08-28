package org.iot.server.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	private BeaconServiceImpl testedObject;
	
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
	public void testGetAllBeaconsStatuses()
	{
		List<BeaconStatusTo> beaconStatusesList = testedObject.getAllBeaconsStatuses();
		
		assertEquals(true, beaconStatusesList != null);
		
		verify(beaconStatusRepository).findAll();

		verify(beaconStatusMapper).mapDocuments2Tos(beaconStatusesListCaptor.capture());
		
		assertEquals(true, beaconStatusesListCaptor.getAllValues() != null);
	}
	
	@Test
	public void testRegisterStatus()
	{
		BeaconStatusTo statusTo = TestDataGenerator.getBeaconStatusTo();
		
		testedObject.registerStatus(statusTo);
		
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
		List<BeaconTo> beaconsList = testedObject.getAllBeacons();
		
		assertNotNull(beaconsList);
		
		verify(beaconRepository).findAll();

		verify(beaconMapper).mapDocuments2Tos(beaconsListCaptor.capture());
		
		assertEquals(true, beaconsListCaptor.getAllValues() != null);
	}
	
	@Test
	public void testAddBeacon()
	{
		BeaconTo beaconTo = TestDataGenerator.getBeaconTo();
		BeaconTo addedData = testedObject.addBeacon(beaconTo);
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
		testedObject.deleteBeacon(beacon.getId());
		
		ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);
		verify(beaconRepository).delete(idCaptor.capture());
		assertEquals(beacon.getId(), idCaptor.getValue());
	}
	
	
	@Test
	public void testUpdateBeacon()
	{
		BeaconTo beaconTo = TestDataGenerator.getBeaconTo();
		BeaconTo addedData = testedObject.updateBeacon(beaconTo);
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