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
		
		beaconStatusTo.setMac(beaconStatus.getMac());
		beaconStatusTo.setMajor(beaconStatus.getMajor());
		beaconStatusTo.setMinor(beaconStatus.getMinor());
		beaconStatusTo.setRssi(beaconStatus.getRssi());
		beaconStatusTo.setTxPower(beaconStatus.getTxPower());
		beaconStatusTo.setUuid(beaconStatus.getUuid());
		
		return beaconStatusTo;
	}
	
	public List<BeaconStatusTo> mapDocuments2Tos(List<BeaconStatus> beaconStatuses) {
		
		/*
		 *  TO LEARN
		 *  
		List<BeaconStatusTo> beaconStatusTos = new ArrayList<>(beaconStatuses.size());

		 for (BeaconStatus beaconStatus : beaconStatuses) {
			beaconStatusTos.add(mapDocument2To(beaconStatus));
		}
		*/
		
		return beaconStatuses.stream().map(this::mapDocument2To).collect(Collectors.toList());
		
	}
	
	public BeaconStatus mapTo2Document(BeaconStatusTo beaconStatusTo) {
		
		BeaconStatus beaconStatus = new BeaconStatus();
		
		beaconStatus.setMac(beaconStatusTo.getMac());
        beaconStatus.setMajor(beaconStatusTo.getMajor());
        beaconStatus.setMinor(beaconStatusTo.getMinor());
        beaconStatus.setRssi(beaconStatusTo.getRssi());
        beaconStatus.setTxPower(beaconStatusTo.getTxPower());
        beaconStatus.setUuid(beaconStatusTo.getUuid());
        
        return beaconStatus;
    }
	
	public List<BeaconStatus> mapTos2Documents(List<BeaconStatus> beaconStatus) {			
		return null;
	}

}
