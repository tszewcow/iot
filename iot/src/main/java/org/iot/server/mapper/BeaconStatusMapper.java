package org.iot.server.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.iot.server.document.BeaconStatus;
import org.iot.server.to.BeaconStatusTo;
import org.springframework.stereotype.Component;

@Component
public class BeaconStatusMapper {

	public BeaconStatusTo mapDocument2To(BeaconStatus beaconStatus) {

		BeaconStatusTo beaconStatusTo = new BeaconStatusTo();

		beaconStatusTo.setId(beaconStatus.getId());
		beaconStatusTo.setMac(beaconStatus.getMac());
		beaconStatusTo.setMajor(beaconStatus.getMajor());
		beaconStatusTo.setMinor(beaconStatus.getMinor());
		beaconStatusTo.setRssi(beaconStatus.getRssi());
		beaconStatusTo.setMeasuredStrenght(beaconStatus.getMeasuredStrenght());
		beaconStatusTo.setUuid(beaconStatus.getUuid());
		beaconStatusTo.setMacAutomaticMobileSet("c4:e9:84:12:91:81");
		beaconStatusTo.setDistance(beaconStatus.getDistance());
		
		return beaconStatusTo;
	}

	public List<BeaconStatusTo> mapDocuments2Tos(List<BeaconStatus> beaconStatuses) {

		return beaconStatuses.stream().map(this::mapDocument2To).collect(Collectors.toList());
	}

	public BeaconStatus mapTo2Document(BeaconStatusTo beaconStatusTo) {

		BeaconStatus beaconStatus = new BeaconStatus();

		beaconStatus.setId(beaconStatusTo.getId());
		beaconStatus.setMac(beaconStatusTo.getMac());
		beaconStatus.setMajor(beaconStatusTo.getMajor());
		beaconStatus.setMinor(beaconStatusTo.getMinor());
		beaconStatus.setRssi(beaconStatusTo.getRssi());
		beaconStatus.setMeasuredStrenght(beaconStatusTo.getMeasuredStrenght());
		beaconStatus.setUuid(beaconStatusTo.getUuid());
		beaconStatus.setMacAutomaticMobileSet("c4:e9:84:12:91:81");
		beaconStatus.setDistance(beaconStatusTo.getDistance());

		return beaconStatus;
	}

}
