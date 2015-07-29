package org.iot.server.mapper;

import java.util.List;

import org.iot.server.document.BeaconStatus;
import org.iot.server.to.BeaconStatusTo;
import org.springframework.stereotype.Component;

@Component
public class BeaconStatusMapper {
	
	public BeaconStatusTo mapDocument2To(BeaconStatus beaconStatus) {
		// TODO
		return null;
	}
	
	public List<BeaconStatusTo> mapDocuments2Tos(List<BeaconStatus> beaconStatuses) {
		// TODO
		return null;
	}
	
	public BeaconStatus mapTo2Document(BeaconStatusTo beaconStatusTo) {
		BeaconStatus beaconStatus = new BeaconStatus();
        beaconStatus.setPosition(beaconStatusTo.getUuidNor());
        return beaconStatus;
    }
	
	public List<BeaconStatus> mapTos2Documents(List<BeaconStatusTo> beaconStatusTos) {
		// TODO
		return null;
	}

}
