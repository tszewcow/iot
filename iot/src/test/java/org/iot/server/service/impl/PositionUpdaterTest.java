package org.iot.server.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.iot.server.document.AutomaticMobileSet;
import org.iot.server.mapper.AutomaticMobileSetMapper;
import org.iot.server.repository.AutomaticMobileSetRepository;
import org.iot.server.to.AutomaticMobileSetTo;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.verification.Times;

public class PositionUpdaterTest {

	@Spy
	private PositionCalculator positionCalculator = new PositionCalculator();
	@Spy
	private AutomaticMobileSetMapper automaticMobileSetMapper = new AutomaticMobileSetMapper();
	@Mock
	private AutomaticMobileSetRepository automaticMobileSetRepository;

	@InjectMocks
	private PositionUpdater positionUpdater;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void updateAutomaticMobileSetPositionsShouldCalculateCorrectCoordinatesForSimpleData() {
		// given
		List<BeaconTo> beacons = new ArrayList<>();
		beacons.add(createExampleBeacon());
		beacons.add(createExampleBeacon1());

		AutomaticMobileSet expectedAutomaticMobileSet = new AutomaticMobileSet();
		expectedAutomaticMobileSet.setxAutomaticMobileSet(515);
		expectedAutomaticMobileSet.setyAutomaticMobileSet(347.5f);

		List<BeaconStatusTo> beaconStatuses = new ArrayList<>();
		beaconStatuses.add(createExampleBeaconStatuses());
		beaconStatuses.add(createExampleBeaconStatuses1());

		List<AutomaticMobileSetTo> automaticMobileSets = new ArrayList<>();
		automaticMobileSets.add(createExampleAutomaticMobileSet());

		// when
		positionUpdater.updateAutomaticMobileSetPositions(beacons, beaconStatuses, automaticMobileSets);
		// then
		ArgumentCaptor<AutomaticMobileSet> argumentCaptor = ArgumentCaptor.forClass(AutomaticMobileSet.class);
		verify(automaticMobileSetRepository).save(argumentCaptor.capture());
		assertEquals(expectedAutomaticMobileSet.getxAutomaticMobileSet(),
				argumentCaptor.getValue().getxAutomaticMobileSet(), 50);
		assertEquals(expectedAutomaticMobileSet.getyAutomaticMobileSet(),
				argumentCaptor.getValue().getyAutomaticMobileSet(), 50);

	}
	
	@Test
	public void updateAutomaticMobileSetPositionsShouldCalclulateCorrectCoordinatesForDifferentMachines() {
		//given
		List<BeaconTo> beacons = new ArrayList<>();
		beacons.add(createExampleBeacon());
		beacons.add(createExampleBeacon1());
		beacons.add(createExampleBeacon2());
		
		AutomaticMobileSet expectedOne = new AutomaticMobileSet();
		expectedOne.setxAutomaticMobileSet(515);
		expectedOne.setyAutomaticMobileSet(347.5f);
		
		AutomaticMobileSet expectedTwo = new AutomaticMobileSet();
		expectedTwo.setxAutomaticMobileSet(531.5f);
		expectedTwo.setyAutomaticMobileSet(466.f);
		
		List<BeaconStatusTo> beaconStatuses = new ArrayList<>();
		beaconStatuses.add(createExampleBeaconStatuses());
		beaconStatuses.add(createExampleBeaconStatuses1());
		beaconStatuses.add(createExampleBeaconStatuses2());
		beaconStatuses.add(createExampleBeaconStatuses3());
		
		List<AutomaticMobileSetTo> automaticMobileSets = new ArrayList<>();
		automaticMobileSets.add(createExampleAutomaticMobileSet());
		automaticMobileSets.add(createExampleAutomaticMobileSet1());
		
		//when
		positionUpdater.updateAutomaticMobileSetPositions(beacons, beaconStatuses, automaticMobileSets);
		
		//then
		ArgumentCaptor<AutomaticMobileSet> argumentCaptor = ArgumentCaptor.forClass(AutomaticMobileSet.class);
		verify(automaticMobileSetRepository, Mockito.times(2)).save(argumentCaptor.capture());
		assertEquals(2, argumentCaptor.getAllValues().size());
		
		assertEquals(expectedOne.getxAutomaticMobileSet(),
				argumentCaptor.getAllValues().get(0).getxAutomaticMobileSet(), 50);
		assertEquals(expectedOne.getyAutomaticMobileSet(),
				argumentCaptor.getAllValues().get(0).getyAutomaticMobileSet(), 50);
		assertEquals(expectedTwo.getxAutomaticMobileSet(),
				argumentCaptor.getAllValues().get(1).getxAutomaticMobileSet(), 20);
		assertEquals(expectedTwo.getyAutomaticMobileSet(),
				argumentCaptor.getAllValues().get(1).getyAutomaticMobileSet(), 20);
		
	}

	private BeaconTo createExampleBeacon() {
		BeaconTo beacons = new BeaconTo();
		beacons.setName("194");
		beacons.setMac("20:fa:bb:01:77:f5");
		beacons.setUuidNormal("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setUuidSecure("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setUuidService("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setBuilding("MT II");
		beacons.setFloor(7);
		beacons.setRoom(7.5);
		beacons.setxBeacon(573);
		beacons.setyBeacon(362);
		return beacons;
	}

	private BeaconTo createExampleBeacon1() {
		BeaconTo beacons = new BeaconTo();
		beacons.setName("193");
		beacons.setMac("20:fa:bb:01:77:e1");
		beacons.setUuidNormal("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setUuidSecure("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setUuidService("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setBuilding("MT II");
		beacons.setFloor(7);
		beacons.setRoom(7.5);
		beacons.setxBeacon(457);
		beacons.setyBeacon(333);
		return beacons;
	}
	
	private BeaconTo createExampleBeacon2() {
		BeaconTo beacons = new BeaconTo();
		beacons.setName("192");
		beacons.setMac("20:fa:bb:01:77:d1");
		beacons.setUuidNormal("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setUuidSecure("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setUuidService("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setBuilding("MT II");
		beacons.setFloor(7);
		beacons.setRoom(7.32);
		beacons.setxBeacon(490);
		beacons.setyBeacon(570);
		return beacons;
	}
	
	private BeaconStatusTo createExampleBeaconStatuses() {
		BeaconStatusTo beaconStatus = new BeaconStatusTo();
		beaconStatus.setMajor(65535);
		beaconStatus.setMinor("194");
		beaconStatus.setMac("20:fa:bb:01:77:f5");
		beaconStatus.setRssi(-65);
		beaconStatus.setMeasuredStrenght(-59);
		beaconStatus.setUuid("1");
		beaconStatus.setMacAutomaticMobileSet("20:sd:sd:sd:23:sd");
		beaconStatus.setDistance(150);
		return beaconStatus;
	}

	private BeaconStatusTo createExampleBeaconStatuses1() {
		BeaconStatusTo beaconStatus = new BeaconStatusTo();
		beaconStatus.setMajor(65535);
		beaconStatus.setMinor("193");
		beaconStatus.setMac("20:fa:bb:01:77:e1");
		beaconStatus.setRssi(-65);
		beaconStatus.setMeasuredStrenght(-59);
		beaconStatus.setUuid("1");
		beaconStatus.setMacAutomaticMobileSet("20:sd:sd:sd:23:sd");
		beaconStatus.setDistance(150);
		return beaconStatus;
	}
	
	private BeaconStatusTo createExampleBeaconStatuses2() {
		BeaconStatusTo beaconStatus = new BeaconStatusTo();
		beaconStatus.setMajor(65535);
		beaconStatus.setMinor("194");
		beaconStatus.setMac("20:fa:bb:01:77:f5");
		beaconStatus.setRssi(-65);
		beaconStatus.setMeasuredStrenght(-59);
		beaconStatus.setUuid("1");
		beaconStatus.setMacAutomaticMobileSet("12:12:12:12:12:12");
		beaconStatus.setDistance(150);
		return beaconStatus;
	}

	private BeaconStatusTo createExampleBeaconStatuses3() {
		BeaconStatusTo beaconStatus = new BeaconStatusTo();
		beaconStatus.setMajor(65535);
		beaconStatus.setMinor("192");
		beaconStatus.setMac("20:fa:bb:01:77:d1");
		beaconStatus.setRssi(-65);
		beaconStatus.setMeasuredStrenght(-59);
		beaconStatus.setUuid("1");
		beaconStatus.setMacAutomaticMobileSet("12:12:12:12:12:12");
		beaconStatus.setDistance(150);
		return beaconStatus;
	}

	private AutomaticMobileSetTo createExampleAutomaticMobileSet() {
		AutomaticMobileSetTo automaticMobileSet = new AutomaticMobileSetTo();
		automaticMobileSet.setBuilding("MT II");
		automaticMobileSet.setxAutomaticMobileSet(150);
		automaticMobileSet.setyAutomaticMobileSet(150);
		automaticMobileSet.setFloor(7);
		automaticMobileSet.setGuardian("Mirek");
		automaticMobileSet.setProject("projekt");
		automaticMobileSet.setMacAutomaticMobileSet("20:sd:sd:sd:23:sd");
		automaticMobileSet.setRoom(7.12);
		automaticMobileSet.setIsActual(false);
		automaticMobileSet.setId("55db0d1e6741df7b705ac336");
		return automaticMobileSet;
	}
	
	private AutomaticMobileSetTo createExampleAutomaticMobileSet1() {
		AutomaticMobileSetTo automaticMobileSet = new AutomaticMobileSetTo();
		automaticMobileSet.setBuilding("MT II");
		automaticMobileSet.setxAutomaticMobileSet(30);
		automaticMobileSet.setyAutomaticMobileSet(560);
		automaticMobileSet.setFloor(7);
		automaticMobileSet.setGuardian("Marek");
		automaticMobileSet.setProject("projekt2");
		automaticMobileSet.setMacAutomaticMobileSet("12:12:12:12:12:12");
		automaticMobileSet.setRoom(7.30);
		automaticMobileSet.setIsActual(false);
		automaticMobileSet.setId("55db0d1e6741df7b705ac337");
		return automaticMobileSet;
	}
}
