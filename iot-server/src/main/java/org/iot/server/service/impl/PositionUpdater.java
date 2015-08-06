package org.iot.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iot.server.to.AutomaticMobileSetTo;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;
import org.springframework.stereotype.Component;

@Component
public class PositionUpdater {

	public void updateAutomaticMobileSetPositions(List<BeaconTo> beacons, List<BeaconStatusTo> beaconStatuses,
			List<AutomaticMobileSetTo> automaticMobileSets) {

		Map<String, List<BeaconStatusTo>> map = groupStatusesByAutomaticMobileSet(beaconStatuses);

		for (Map.Entry<String, List<BeaconStatusTo>> entry : map.entrySet()) {
			List<BeaconStatusTo> automaticMobileSetBeaconStatuses = entry.getValue();
			Map<String, List<BeaconStatusTo>> beaconToBeaconStatuses = groupStatusesByBeacon(beaconStatuses);
			Map<String, Float> beaconToDistance = calculateDistance(beaconToBeaconStatuses);
			String mac = entry.getKey();
			AutomaticMobileSetTo automaticMobileSet = getAutomaticMobileSetByMac(automaticMobileSets, mac);
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

	private AutomaticMobileSetTo getAutomaticMobileSetByMac(List<AutomaticMobileSetTo> automaticMobileSets,
			String mac) {
		for (AutomaticMobileSetTo automaticMobileSet : automaticMobileSets) {
			String currentMac = automaticMobileSet.getMacAutomaticMobileSet();
			if (currentMac.equals(mac)) {
				return automaticMobileSet;
			}
		}
		throw new IllegalStateException("automaticMobileSet with mac " + mac + " has not been found.");
	}

	private Map<String, Float> calculateDistance(Map<String, List<BeaconStatusTo>> beaconMacToBeaconStatuses) {
		Map<String, Float> map = new HashMap<>();
		for (Map.Entry<String, List<BeaconStatusTo>> entry : beaconMacToBeaconStatuses.entrySet()) {
			map.put(entry.getKey(), calculateAverageDistance(entry.getValue()));
		}
		return map;
	}

	private Float calculateAverageDistance(List<BeaconStatusTo> value) {

		// TODO Average distance

		float distance = 0;
		float avarageDistance = 0;
		int counter = 0;

		for (BeaconStatusTo beaconData : value) {
			distance = (float) (Math.pow(10d, beaconData.getMeasuredStrenght() - beaconData.getRssi()) / (10 * 2));
			avarageDistance = +distance;
			counter++;
		}
		return avarageDistance / counter;
	}

	private String calculateCoordinates(Map<String, Float> beaconToDistance, List<BeaconTo> beacons) {
		// TODO Auto-generated method stub
		return null;
	}
}
