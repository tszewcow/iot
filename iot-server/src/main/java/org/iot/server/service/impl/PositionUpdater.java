package org.iot.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.iot.server.to.AutomaticMobileSetTo;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;

public class PositionUpdater {

	public void updateAutomaticMobileSetPositions(List<BeaconTo> beacons, List<BeaconStatusTo> beaconStatuses,
			List<AutomaticMobileSetTo> automaticMobileSets) {

		List<BeaconsStatusesForPositon> choosenBeaconStatuses = new ArrayList<>();

		for (AutomaticMobileSetTo automaticMobileSet : automaticMobileSets)
			for (BeaconStatusTo beaconStatusTo : beaconStatuses)
				for (BeaconTo beaconTo : beacons) {
					// 1. choose beacon statuses for given automatic mobile set
					// new parameter in Python script needed

					List<BeaconsStatusesForPositon> automaticMobileSetBeaconStatuses = chooseBeaconStatuses(
							automaticMobileSet, beaconStatusTo, beaconTo, choosenBeaconStatuses);

					String position = calculateCoordinates(automaticMobileSetBeaconStatuses);

					automaticMobileSet.setPosition(position);
				}
	}

	private List<BeaconsStatusesForPositon> chooseBeaconStatuses(AutomaticMobileSetTo automaticMobileSet,
			BeaconStatusTo beaconStatuses, BeaconTo beacons, List<BeaconsStatusesForPositon> choosenBeaconStatuses) {

		BeaconsStatusesForPositon beaconsStatusesForPosition = new BeaconsStatusesForPositon();

		beaconsStatusesForPosition.setMacBeacon(beacons.getMac());
		beaconsStatusesForPosition.setxBeacon(beacons.getxBeacon());
		beaconsStatusesForPosition.setyBeacon(beacons.getyBeacon());
		beaconsStatusesForPosition.setRoomBeacon(beacons.getRoom());
		beaconsStatusesForPosition.setFloorBeacon(beacons.getFloor());

		beaconsStatusesForPosition.setMacAutomaticMobileSet(automaticMobileSet.getMacAutomaticMobileSet());
		beaconsStatusesForPosition.setxAutomaticMobileSet(automaticMobileSet.getX());
		beaconsStatusesForPosition.setyAutomaticMobileSet(automaticMobileSet.getY());
		beaconsStatusesForPosition.setRoomAutomaticMobileSet(automaticMobileSet.getRoom());
		beaconsStatusesForPosition.setFloorAutomaticMobileSet(automaticMobileSet.getFloor());

		beaconsStatusesForPosition.setDistance(beaconStatuses.getDistance());

		choosenBeaconStatuses.add(beaconsStatusesForPosition);

		return choosenBeaconStatuses;
	}

	private String calculateCoordinates(List<BeaconsStatusesForPositon> automaticMobileSetBeaconStatuses) {

		// TODO implement
		return null;
	}

}
