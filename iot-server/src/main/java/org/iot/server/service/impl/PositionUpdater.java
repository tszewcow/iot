package org.iot.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iot.server.to.AutomaticMobileSetTo;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;

public class PositionUpdater {

	public void updateAutomaticMobileSetPositions(List<BeaconTo> beacons, List<BeaconStatusTo> beaconStatuses,
			List<AutomaticMobileSetTo> automaticMobileSets) {

		Map<String, List<BeaconStatusTo>> map = groupStatusesByAutomaticMobileSet(beaconStatuses);

		for (Map.Entry<String, List<BeaconStatusTo>> entry : map.entrySet()) {
			List<BeaconStatusTo> automaticMobileSetBeaconStatuses = entry.getValue();
			Map<String, List<BeaconStatusTo>> beaconToBeaconStatuses = groupStatusesByBeacon(beaconStatuses);
			Map<String, Float> beaconToDistance = calculateDistance(beaconToBeaconStatuses);
			String mac = entry.getKey();
			AutomaticMobileSetTo automaticMobileSet = findAutomaticMobileSetByMac(automaticMobileSets, mac);
			String coordinates = calculateCoordinates(beaconToDistance, beacons);
			// TODO set x, y from coordinates
			automaticMobileSet.setxAutomaticMobileSet(0);
		}
	}

	private Map<String, List<BeaconStatusTo>> groupStatusesByAutomaticMobileSet(List<BeaconStatusTo> beaconStatuses) {
		Map<String, List<BeaconStatusTo>> map = new HashMap<>();

		for (BeaconStatusTo beaconStatus : beaconStatuses) {
			String automaticMobileSetMac = beaconStatus.getMacAutomaticMobileSet();

			List<BeaconStatusTo> beaconStatusesForMobileSet = map.get(automaticMobileSetMac);
			if (beaconStatusesForMobileSet == null) {
				beaconStatusesForMobileSet = new ArrayList<>();
				map.put(automaticMobileSetMac, beaconStatusesForMobileSet);
			}
			beaconStatusesForMobileSet.add(beaconStatus);
		}
		return map;
	}

	private Map<String, List<BeaconStatusTo>> groupStatusesByBeacon(List<BeaconStatusTo> beaconStatuses) {
		Map<String, List<BeaconStatusTo>> map = new HashMap<>();

		for (BeaconStatusTo beaconStatusTo : beaconStatuses) {
			String beaconMac = beaconStatusTo.getMac();
			List<BeaconStatusTo> beaconStatusesForBeacon = map.get(beaconMac);
			if (beaconStatusesForBeacon == null) {
				beaconStatusesForBeacon = new ArrayList<>();
				map.put(beaconMac, beaconStatusesForBeacon);
			}
			beaconStatusesForBeacon.add(beaconStatusTo);
		}
		return map;
	}

	private AutomaticMobileSetTo findAutomaticMobileSetByMac(List<AutomaticMobileSetTo> automaticMobileSets,
			String mac) {

		AutomaticMobileSetTo automaticMobileSetMacIden = new AutomaticMobileSetTo();
		// for (AutomaticMobileSetTo automaticMobileSet : automaticMobileSets)

		// TODO Auto-generated method stub

		return null;
	}

	private Map<String, Float> calculateDistance(Map<String, List<BeaconStatusTo>> beaconMacToBeaconStatuses) {
		Map<String, Float> map = new HashMap<>();
		for (Map.Entry<String, List<BeaconStatusTo>> entry : beaconMacToBeaconStatuses.entrySet()) {
			map.put(entry.getKey(), calculateDistance(entry.getValue()));
		}
		return map;
	}

	private Float calculateDistance(List<BeaconStatusTo> value) {
		// TODO Auto-generated method stub
		return null;
	}

	private String calculateCoordinates(Map<String, Float> beaconToDistance, List<BeaconTo> beacons) {
		// TODO Auto-generated method stub
		return null;
	}
}
