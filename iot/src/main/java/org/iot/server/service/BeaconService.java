package org.iot.server.service;

import java.util.List;

import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;

public interface BeaconService {

	List<BeaconTo> getAllBeacons();

	void registerStatus(BeaconStatusTo status);

	List<BeaconStatusTo> getAllBeaconsStatuses();

	BeaconTo addBeacon(BeaconTo beacon);

	void deleteBeacon(String id);

	BeaconTo updateBeacon(BeaconTo request);
}
