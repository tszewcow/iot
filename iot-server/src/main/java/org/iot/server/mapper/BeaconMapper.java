package org.iot.server.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.iot.server.document.Beacon;
import org.iot.server.to.BeaconTo;
import org.springframework.stereotype.Component;

@Component
public class BeaconMapper {

	public BeaconTo mapDocument2To(Beacon beacon) {

		BeaconTo beaconTo = new BeaconTo();

		beaconTo.setName(beacon.getName());
		beaconTo.setMac(beacon.getMac());
		beaconTo.setUuidNormal(beacon.getUuidNormal());
		beaconTo.setUuidSecure(beacon.getUuidSecure());
		beaconTo.setUuidService(beacon.getUuidService());
		beaconTo.setBuilding(beacon.getBuilding());
		beaconTo.setFloor(beacon.getFloor());
		beaconTo.setRoom(beacon.getRoom());
		beaconTo.setxBeacon(beacon.getxBeacon());
		beaconTo.setyBeacon(beacon.getyBeacon());

		return beaconTo;
	}

	public List<BeaconTo> mapDocuments2Tos(List<Beacon> beacons) {

		return beacons.stream().map(this::mapDocument2To).collect(Collectors.toList());
	}

	public Beacon mapTo2Document(BeaconTo beaconTo) {

		Beacon beacon = new Beacon();

		beacon.setName(beaconTo.getName());
		beacon.setMac(beaconTo.getMac());
		beacon.setUuidNormal(beaconTo.getUuidNormal());
		beacon.setUuidSecure(beaconTo.getUuidSecure());
		beacon.setUuidService(beaconTo.getUuidService());
		beacon.setBuilding(beaconTo.getBuilding());
		beacon.setFloor(beaconTo.getFloor());
		beacon.setRoom(beaconTo.getRoom());
		beacon.setxBeacon(beaconTo.getxBeacon());
		beacon.setyBeacon(beaconTo.getyBeacon());

		return beacon;
	}

}
