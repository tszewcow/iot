package org.iot.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.iot.server.to.AutomaticMobileSetTo;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;
import org.junit.Before;
import org.junit.Test;

public class PositionUpdaterTest {

	private PositionUpdater positionUpdater;

	@Before
	public void setUp() {
		PositionCalculator positionCalculator = new PositionCalculator();
		positionUpdater = new PositionUpdater(positionCalculator);
	}

	@Test
	public void updateAutomaticMobileSetPositionsShouldCalculateCorrectCoordinatesForSimpleData() {
		// given
		List<BeaconTo> beacons = new ArrayList<>();
		beacons.add(createExampleBeacon());
		beacons.add(createExampleBeacon1());
		beacons.add(createExampleBeacon2());

		List<BeaconStatusTo> beaconStatuses = new ArrayList<>();
		beaconStatuses.add(createExampleBeaconStatuses());
		beaconStatuses.add(createExampleBeaconStatuses1());
		beaconStatuses.add(createExampleBeaconStatuses2());
		beaconStatuses.add(createExampleBeaconStatuses3());

		List<AutomaticMobileSetTo> automaticMobileSets = new ArrayList<>();
		automaticMobileSets.add(createExampleAutomaticMobileSet());

		// when
		positionUpdater.updateAutomaticMobileSetPositions(beacons, beaconStatuses, automaticMobileSets);
		// then
		// TODO Add assertions on automatic mobile sets coordinates

	}

	private BeaconTo createExampleBeacon() {
		BeaconTo beacons = new BeaconTo();
		beacons.setName("MT II 7.p 12.pokój (pokój Jacka)");
		beacons.setMac("D6:90:A8:08:F0:E0");
		beacons.setUuidnor("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setUuidsec("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setUuidser("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setBuilding("MT II");
		beacons.setFloor(7);
		beacons.setRoom(7.5);
		beacons.setxBeacon(5);
		beacons.setyBeacon(0);
		return beacons;
	}

	private BeaconTo createExampleBeacon1() {
		BeaconTo beacons = new BeaconTo();
		beacons.setName("MT II 7.p 12.pokój (pokój Jacka)");
		beacons.setMac("D6:90:A8:08:F0:E1");
		beacons.setUuidnor("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setUuidsec("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setUuidser("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setBuilding("MT II");
		beacons.setFloor(7);
		beacons.setRoom(7.5);
		beacons.setxBeacon(140);
		beacons.setyBeacon(130);
		return beacons;
	}

	private BeaconTo createExampleBeacon2() {
		BeaconTo beacons = new BeaconTo();
		beacons.setName("MT II 7.p 12.pokój (pokój Jacka)");
		beacons.setMac("D6:90:A8:08:F0:E2");
		beacons.setUuidnor("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setUuidsec("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setUuidser("00001800-0000-1000-8000-00805f9b34fb");
		beacons.setBuilding("MT II");
		beacons.setFloor(7);
		beacons.setRoom(7.5);
		beacons.setxBeacon(150);
		beacons.setyBeacon(160);
		return beacons;
	}

	private BeaconStatusTo createExampleBeaconStatuses() {
		BeaconStatusTo beaconStatus = new BeaconStatusTo();
		beaconStatus.setMajor(65535);
		beaconStatus.setMinor("189");
		beaconStatus.setMac("D6:90:A8:08:F0:E0");
		beaconStatus.setRssi(-50);
		beaconStatus.setMeasuredStrenght(-61);
		beaconStatus.setUuid("a271b5f0092c01333a737a1048141ee1");
		beaconStatus.setMacAutomaticMobileSet("20:sd:sd:sd:23:sd");
		beaconStatus.setDistance(3);
		return beaconStatus;
	}

	private BeaconStatusTo createExampleBeaconStatuses2() {
		BeaconStatusTo beaconStatus = new BeaconStatusTo();
		beaconStatus.setMajor(65535);
		beaconStatus.setMinor("190");
		beaconStatus.setMac("D6:90:A8:08:F0:E1");
		beaconStatus.setRssi(-50);
		beaconStatus.setMeasuredStrenght(-61);
		beaconStatus.setUuid("a271b5f0092c01333a737a1048141ee2");
		beaconStatus.setMacAutomaticMobileSet("20:sd:sd:sd:23:sd");
		beaconStatus.setDistance(5);
		return beaconStatus;
	}

	private BeaconStatusTo createExampleBeaconStatuses1() {
		BeaconStatusTo beaconStatus = new BeaconStatusTo();
		beaconStatus.setMajor(65535);
		beaconStatus.setMinor("189");
		beaconStatus.setMac("D6:90:A8:08:F0:E2");
		beaconStatus.setRssi(-50);
		beaconStatus.setMeasuredStrenght(-61);
		beaconStatus.setUuid("a271b5f0092c01333a737a1048141ee2");
		beaconStatus.setMacAutomaticMobileSet("20:sd:sd:sd:23:sd");
		beaconStatus.setDistance(1.5);
		return beaconStatus;
	}

	private BeaconStatusTo createExampleBeaconStatuses3() {
		BeaconStatusTo beaconStatus = new BeaconStatusTo();
		beaconStatus.setMajor(65535);
		beaconStatus.setMinor("189");
		beaconStatus.setMac("D6:90:A8:08:F0:E0");
		beaconStatus.setRssi(-50);
		beaconStatus.setMeasuredStrenght(-61);
		beaconStatus.setUuid("a271b5f0092c01333a737a1048141ee1");
		beaconStatus.setMacAutomaticMobileSet("20:sd:sd:sd:23:sd");
		beaconStatus.setDistance(10);
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
		return automaticMobileSet;
	}
}
