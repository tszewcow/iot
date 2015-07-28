package org.iot.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.iot.server.service.BeaconService;
import org.iot.server.to.BeaconTo;
import org.springframework.stereotype.Service;

@Service
public class BeaconServiceImpl implements BeaconService {

	@Override
	public List<BeaconTo> getAllBeacons() {
		List<BeaconTo> beacons = new ArrayList<>();
		
		BeaconTo beacon = createExampleBeacon();
		BeaconTo beacon1 = createExampleBeacon1();
		BeaconTo beacon2 = createExampleBeacon2();
		
		beacons.add(beacon);
		beacons.add(beacon1);
		beacons.add(beacon2);
		
		return beacons;
	}
	

	private BeaconTo createExampleBeacon() {
		BeaconTo beacon = new BeaconTo();		
		beacon.setName("MT II 7.p 12.pokój (pokój Jacka)");
		beacon.setMac("D6:90:A8:08:F0:E4");
		beacon.setUuidnor("00001800-0000-1000-8000-00805f9b34fb");
		beacon.setUuidsec("00001800-0000-1000-8000-00805f9b34fb");
		beacon.setUuidser("00001800-0000-1000-8000-00805f9b34fb");
		beacon.setBuilding("MT II");
		beacon.setFloor("7");
		beacon.setRoom("7.5");
		beacon.setCoordinates("145x45");
		return beacon;
	}
	private BeaconTo createExampleBeacon1(){
		BeaconTo beacon1 = new BeaconTo();
		beacon1.setName("n/a");
		beacon1.setMac("n/a");
		beacon1.setUuidnor("n/a");
		beacon1.setUuidsec("n/a");
		beacon1.setUuidser("n/a");
		beacon1.setBuilding("MT II");
		beacon1.setFloor("11");
		beacon1.setRoom("11.2");
		beacon1.setCoordinates("12x231");
		return beacon1;
	}
	private BeaconTo createExampleBeacon2() {
		BeaconTo beacon2 = new BeaconTo();		
		beacon2.setName("MT II 8.p 132.pokój");
		beacon2.setMac("D6:90:A8:08:F0:E4");
		beacon2.setUuidnor("00001800-0000-1000-1231-00805f9b34fb");
		beacon2.setUuidsec("00001800-0000-4553-8000-00805f9b34fb");
		beacon2.setUuidser("00001800-0000-3456-8000-00805f9b34fb");
		beacon2.setBuilding("MT II");
		beacon2.setFloor("8");
		beacon2.setRoom("8.132");
		beacon2.setCoordinates("145x15");
		return beacon2;		
	}
}