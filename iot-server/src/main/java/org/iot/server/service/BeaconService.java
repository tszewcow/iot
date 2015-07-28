package org.iot.server.service;

import java.util.List;

import org.iot.server.to.BeaconTo;

public interface BeaconService {
	
	List<BeaconTo> getAllBeacons();

}
